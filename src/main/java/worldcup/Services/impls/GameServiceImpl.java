package worldcup.Services.impls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import worldcup.GameStage;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.GameService;
import worldcup.Services.interfaces.SoccerPlayersService;
import worldcup.api.dtos.GameDto;
import worldcup.api.dtos.GameResultDto;
import worldcup.api.dtos.GamesResponseDto;
import worldcup.persistance.entities.Game;
import worldcup.persistance.entities.SoccerPlayer;
import worldcup.persistance.repository.GameRepository;
import worldcup.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    public static final int GROUP_STAGE_GAME_COUNT = 48;
    public static final int TOTAL_GAME_COUNT = 64;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private SoccerPlayersService soccerPlayersService;

    @Autowired
    private ConverterService converterService;

    @Override
    public boolean isKnockOutStage() {
        return getGamesByStage(GameStage.Group).
                stream().
                filter(this::isGameFinished).
                count() == GROUP_STAGE_GAME_COUNT;
    }

    @Override
    public boolean isTournamentFinished() {
        return getFinishedGames().size() >= TOTAL_GAME_COUNT;
    }

    @Override
    public List<Game> getAllGames() {
        return Lists.newArrayList(gameRepository.findAll());
    }

    @Override
    public List<Game> getFinishedGames() {
        return getAllGames().stream().
                filter(this::isGameFinished).
                collect(Collectors.toList());
    }

    @Override
    public Game findById(Long gameId) {
        Optional<Game> byId = gameRepository.findById(gameId);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuntimeException("Game with Id " + gameId + " doesn't exist");
        }
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGameResult(GameResultDto gameResult) {
        Game game = validateGameResult(gameResult);
        updateGameResult(gameResult, game);
        updateScorers(gameResult);
        return game;
    }

    @Override
    public GamesResponseDto getAllGamesByGameTime(String gameStage) {
        GamesResponseDto gamesDto = new GamesResponseDto();
        Date now = new Date();

        List<Game> gamesList;
        if (gameStage != null) {
            gamesList = getGamesByStage(GameStage.valueOf(gameStage));
        } else {
            gamesList = getAllGames();
        }
        gamesList.forEach(game -> {
            GameDto gameDto = converterService.covertGameToGameDto(game);
            if (DateUtils.isToday(game.getGameTime())) {
                gamesDto.getToday().add(gameDto);
            } else if (DateUtils.isBeforeDay(game.getGameTime(), now)) {
                gamesDto.getFinished().add(gameDto);
            } else {
                gamesDto.getFuture().add(gameDto);
            }
        });
        return gamesDto;
    }

    @Override
    public List<Game> getGamesByStage(GameStage gameStage) {
        return Lists.newArrayList(gameRepository.findGamesByLevel(gameStage));
    }

    private void updateScorers(GameResultDto gameResult) {
        Map<String, SoccerPlayer> scorersMap = soccerPlayersService.getAllSoccerPlayersByName();
        if (gameResult != null) {
            gameResult.getSoccerPlayers()
                    .stream()
                    .forEach(player -> {
                        if (scorersMap.containsKey(player.getName())) {
                            scorersMap.get(player.getName()).addGoals(player.getNumberOfGoals());
                            soccerPlayersService.save(scorersMap.get(player.getName()));
                        } else {
                            SoccerPlayer newSoccerPlayer = new SoccerPlayer(player.getName(), player.getNumberOfGoals());
                            SoccerPlayer savedSoccerPlayer = soccerPlayersService.save(newSoccerPlayer);
                            scorersMap.put(savedSoccerPlayer.getName(), savedSoccerPlayer);
                        }
                    });
        }
    }

    private void updateGameResult(@RequestBody GameResultDto gameResult, Game game) {
        game.setScore1(gameResult.getScore1());
        game.setScore2(gameResult.getScore2());
        save(game);
    }

    private Game validateGameResult(GameResultDto gameResult) {
        Game game = findById(gameResult.getId());
        if (gameResult.getScore1() == null || gameResult.getScore1() < 0) {
            throw new RuntimeException("Score 1 can't be negative nor NULL");
        }
        if (gameResult.getScore2() == null || gameResult.getScore2() < 0) {
            throw new RuntimeException("Score 2 can't be negative nor NULL");
        }

        if (gameResult != null) {
            gameResult.getSoccerPlayers()
                    .stream()
                    .forEach(player -> {
                        if (player.getName() == null) {
                            throw new RuntimeException("Player name can't be NULL");
                        }
                        if (player.getNumberOfGoals() == null || player.getNumberOfGoals() < 0) {
                            throw new RuntimeException("Player number of goals can't be negative nor NULL");
                        }
                    });
        }

        return game;
    }

    private boolean isGameFinished(Game game) {
        return game.getScore1() != null && game.getScore2() != null;
    }
}
