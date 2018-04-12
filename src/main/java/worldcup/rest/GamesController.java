package worldcup.rest;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worldcup.Services.PointsCalculatorService;
import worldcup.dtos.GameResultDto;
import worldcup.dtos.GamesResponseDto;
import worldcup.entities.Game;
import worldcup.entities.SoccerPlayer;
import worldcup.repository.GameRepository;
import worldcup.repository.SoccerPlayerRepository;
import worldcup.utils.DateUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/games")
public class GamesController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private SoccerPlayerRepository soccerPlayerRepository;
    @Autowired
    private PointsCalculatorService pointsCalculatorService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllGames() {
        GamesResponseDto games = new GamesResponseDto();
        Date now = new Date();

        Iterable<Game> all = gameRepository.findAll();
        Lists.newArrayList(all).forEach(game -> {
            if(DateUtils.isToday(game.getGameTime())) {
                games.getToday().add(game);
            } else if(DateUtils.isBeforeDay(game.getGameTime(), now)) {
                games.getFinished().add(game);
            } else {
                games.getFuture().add(game);
            }
        });
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{gameId}")
    @ResponseBody
    public ResponseEntity<?> getGameById(@PathVariable Long gameId){
        return new ResponseEntity<>(gameRepository.findById(gameId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> updateGameResult(@RequestBody GameResultDto gameResult){
        Game game = validateGameResult(gameResult);
        updateGameResult(gameResult, game);
        updateScorers(gameResult, game);
        pointsCalculatorService.calculateUsersPoint();
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    private void updateScorers(GameResultDto gameResult, Game game) {
        Iterable<SoccerPlayer> allScorers = soccerPlayerRepository.findAll();
        Map<String, SoccerPlayer> scorersMap = Lists.newArrayList(allScorers)
                .stream()
                .collect(Collectors.toMap(x -> x.getName(), Function.identity()));
        if(gameResult!=null) {
            gameResult.getSoccerPlayers()
                    .stream()
                    .forEach(player -> {
                        if(scorersMap.containsKey(player.getName())) {
                            scorersMap.get(player.getName()).addGoals(player.getNumberOfGoals());
                            soccerPlayerRepository.save(scorersMap.get(player.getName()));
                        } else {
                            SoccerPlayer newSoccerPlayer = new SoccerPlayer(player.getName(), player.getNumberOfGoals());
                            SoccerPlayer savedSoccerPlayer = soccerPlayerRepository.save(newSoccerPlayer);
                            scorersMap.put(savedSoccerPlayer.getName(), savedSoccerPlayer);
                        }
                    });
        }
    }

    private void updateGameResult(@RequestBody GameResultDto gameResult, Game game) {
        game.setScore1(gameResult.getScore1());
        game.setScore2(gameResult.getScore2());
        gameRepository.save(game);
    }

    private Game validateGameResult(GameResultDto gameResult) {
        Optional<Game> game = gameRepository.findById(gameResult.getId());
        if(!game.isPresent()) {
            throw new RuntimeException("Game with Id " + gameResult.getId() + " doesn't exist");
        }
        if(gameResult.getScore1()==null || gameResult.getScore1()<0) {
            throw new RuntimeException("Score 1 can't be negative nor NULL");
        }
        if(gameResult.getScore2()== null || gameResult.getScore2()<0) {
            throw new RuntimeException("Score 2 can't be negative nor NULL");
        }

        if(gameResult!=null) {
            gameResult.getSoccerPlayers()
                    .stream()
                    .forEach(player -> {
                        if (player.getName()==null) {
                            throw new RuntimeException("Player name can't be NULL");
                        }
                        if (player.getNumberOfGoals()==null || player.getNumberOfGoals()<0) {
                            throw new RuntimeException("Player number of goals can't be negative nor NULL");
                        }
                    });
        }

        return game.get();
    }
}
