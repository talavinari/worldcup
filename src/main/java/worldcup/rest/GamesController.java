package worldcup.rest;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worldcup.entities.Game;
import worldcup.repository.GameRepository;

import java.util.ArrayList;
@RestController
@RequestMapping(path = "/games")
public class GamesController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllGames() {
        Iterable<Game> all = gameRepository.findAll();
        ArrayList<Game> games = Lists.newArrayList(all);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{gameId}")
    @ResponseBody
    public ResponseEntity<?> getGameById(@PathVariable Long gameId){
        return new ResponseEntity<>(gameRepository.findById(gameId), HttpStatus.OK);
    }
}
