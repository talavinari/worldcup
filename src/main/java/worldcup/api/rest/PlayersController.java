package worldcup.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.SoccerPlayersService;


@RestController
@RequestMapping(path = "/players")
public class PlayersController {

    private static final Logger logger = LoggerFactory.getLogger(PlayersController.class);

    @Autowired
    private SoccerPlayersService soccerPlayersService;

    @Autowired
    private ConverterService converterService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllPlayers() {
        return new ResponseEntity<>(converterService.convertPlayersToPlayerString(soccerPlayersService.getAllSoccerPlayers()), HttpStatus.OK);
    }

}
