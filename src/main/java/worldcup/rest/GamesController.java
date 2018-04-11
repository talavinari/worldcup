package worldcup.rest;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worldcup.dtos.GamesResponseDto;
import worldcup.entities.Game;
import worldcup.repository.GameRepository;
import worldcup.utils.DateUtils;

import java.util.Date;

@RestController
@RequestMapping(path = "/games")
public class GamesController {

    @Autowired
    private GameRepository gameRepository;

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
}
