package worldcup.Services.impls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.Services.enums.GameStage;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.GameService;
import worldcup.Services.interfaces.SoccerPlayersService;
import worldcup.Services.interfaces.TeamsService;
import worldcup.api.dtos.*;
import worldcup.persistance.entities.Game;
import worldcup.persistance.entities.SoccerPlayer;
import worldcup.persistance.entities.Team;
import worldcup.persistance.repository.GameRepository;
import worldcup.utils.DateUtils;

import java.util.*;
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

    @Autowired
    private TeamsService teamsService;

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
    public Game updateGameResult(Long gameId, GameResultDto gameResult) {
        Game game = validateGameResult(gameId, gameResult);
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
            GameResponseDto gameResponseDto = converterService.covertGameToGameDto(game);
            if (DateUtils.isToday(game.getGameTime())) {
                gamesDto.getToday().add(gameResponseDto);
            } else if (DateUtils.isBeforeDay(game.getGameTime(), now)) {
                gamesDto.getFinished().add(gameResponseDto);
            } else {
                gamesDto.getFuture().add(gameResponseDto);
            }
        });
        return gamesDto;
    }

    @Override
    public List<Game> getGamesByStage(GameStage gameStage) {
        return Lists.newArrayList(gameRepository.findGamesByLevel(gameStage));
    }

    @Override
    public Map<GameStage, List<String>> getTeamsByStages() {
        Map<GameStage, List<String>> map = new HashMap<>();
        Arrays.asList(GameStage.values())
                .forEach(stage->map.put(stage, new ArrayList<>()));

        map.entrySet()
                .forEach(entry-> {
                    getGamesByStage(entry.getKey())
                            .forEach(game -> {
                                entry.getValue().add(game.getTeam1());
                                entry.getValue().add(game.getTeam2());
                            });
                });
        return map;
    }

    @Override
    public Game updateGameMetadata(GameMetadataDto gameMetadataDto, Long gameId) {
        Game game = findById(gameId);
        validateForDataUpdate(gameMetadataDto, game);
        updateGameMetadataEntity(gameMetadataDto,game);
        return game;
    }

    @Override
    public Game createGame(NewGameDto dto) {
        Game newGame = new Game();
        newGame.setTeam1(dto.getTeam1());
        newGame.setTeam2(dto.getTeam2());
        //newGame.setGameTime(newGame.getGameTime());
        newGame.setGameTime(dto.getGameTime());
        newGame.setStage(GameStage.valueOf(dto.getStage()));
        newGame.setShouldOverride(dto.getShouldOverride());
        return gameRepository.save(newGame);
    }

    private void updateGameMetadataEntity(GameMetadataDto gameMetadataDto, Game game) {
        game.setTeam1(gameMetadataDto.getTeam1());
        game.setTeam2(gameMetadataDto.getTeam2());
        save(game);
    }

    private void validateForDataUpdate(GameMetadataDto gameMetadataDto, Game game) {
        ArrayList<Team> allTeams = teamsService.getAllTeams();
        if (!allTeams.contains(new Team(gameMetadataDto.getTeam1()))){
            throw new RuntimeException("Team1 is invalid team");
        }

        if (!allTeams.contains(new Team(gameMetadataDto.getTeam2()))){
            throw new RuntimeException("Team2 is invalid team");
        }

        if (gameMetadataDto.getTeam2().equals(gameMetadataDto.getTeam1())){
            throw new RuntimeException("Team 1 & 2 should be different");
        }

        if (!game.getShouldOverride()){
            throw new RuntimeException("Game with id " + game.getId() + " is not updatable");
        }
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

    private void updateGameResult(GameResultDto gameResult, Game game) {
        game.setScore1(gameResult.getScore1());
        game.setScore2(gameResult.getScore2());
        if (gameResult.getExtraTimeScore1() != null){
            game.setExtraTimeScore1(gameResult.getExtraTimeScore1());
            game.setExtraTimeScore2(gameResult.getExtraTimeScore2());
        }

        if (gameResult.getPenaltyScore1() != null){
            game.setPenaltyScore1(gameResult.getPenaltyScore1());
            game.setPenaltyScore2(gameResult.getPenaltyScore2());
        }

        save(game);
    }

    private Game validateGameResult(Long gameId, GameResultDto gameResult) {
        Game game = findById(gameId);
        validateForResultUpdate(gameResult);

        return game;
    }


    private void validateForResultUpdate(GameResultDto gameResult) {
        if (gameResult.getScore1() == null || gameResult.getScore1() < 0) {
            throw new RuntimeException("Score 1 can't be negative nor NULL");
        }
        if (gameResult.getScore2() == null || gameResult.getScore2() < 0) {
            throw new RuntimeException("Score 2 can't be negative nor NULL");
        }

        if (gameResult.getExtraTimeScore1() != null && gameResult.getExtraTimeScore1() < 0){
            throw new RuntimeException("Score 1 of extra time can't be NULL");
        }

        if (gameResult.getExtraTimeScore2() != null && gameResult.getExtraTimeScore2() < 0){
            throw new RuntimeException("Score 2 of extra time can't be NULL");
        }

        if (gameResult.getPenaltyScore1() != null && gameResult.getPenaltyScore1() < 0){
            throw new RuntimeException("Score 1 of penalty kicks can't be NULL");
        }

        if (gameResult.getPenaltyScore2() != null && gameResult.getPenaltyScore2() < 0){
            throw new RuntimeException("Score 2 of penalty kicks can't be NULL");
        }

        if (gameResult.getExtraTimeScore1() != null && gameResult.getExtraTimeScore2() == null) {
            throw new RuntimeException("Score 2 of extra time must be provided if Score1 of extra time provided");
        }

        if (gameResult.getExtraTimeScore2() != null && gameResult.getExtraTimeScore1() == null) {
            throw new RuntimeException("Score 1 of extra time must be provided if Score2 provided");
        }

        if (gameResult.getPenaltyScore1() != null && gameResult.getPenaltyScore2() == null) {
            throw new RuntimeException("Score2 of penalty kick must be provided if Score1 of penalty provided");
        }

        if (gameResult.getPenaltyScore2() != null && gameResult.getPenaltyScore1() == null) {
            throw new RuntimeException("Score1 of penalty kick must be provided if Score2 of penalty provided");
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
    }

    private boolean isGameFinished(Game game) {
        return game.getScore1() != null && game.getScore2() != null;
    }
}
