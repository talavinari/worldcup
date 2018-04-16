package worldcup.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.GameService;
import worldcup.Services.interfaces.PointsCalculatorService;
import worldcup.api.dtos.GameMetadataDto;
import worldcup.api.dtos.GameResultDto;
import worldcup.api.dtos.GamesResponseDto;
import worldcup.persistance.entities.Game;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path = "/games")
public class GamesController {

    @Autowired
    private GameService gameService;

    @Autowired
    private PointsCalculatorService pointsCalculatorService;

    @Autowired
    private ConverterService converterService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllGamesByGameTime(@RequestParam(name = "stage", required = false) String stage) {
        GamesResponseDto allGamesByGameTime = gameService.getAllGamesByGameTime(stage);
        return new ResponseEntity<>(allGamesByGameTime, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{gameId}")
    @ResponseBody
    public ResponseEntity<?> getGameById(@PathVariable Long gameId){
        return new ResponseEntity<>(converterService.covertGameToGameDto(gameService.findById(gameId)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> updateGameResult(@RequestBody GameResultDto gameResult){
        Game game = gameService.updateGameResult(gameResult);
        pointsCalculatorService.calculateUsersPoint();
        return new ResponseEntity<>(converterService.covertGameToGameDto(game), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/{gameId}/metadata")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> updateGameMetadata(@PathVariable Long gameId,
                                                    @RequestBody GameMetadataDto gameMetadataDto){
        Game game = gameService.updateGameMetadata(gameMetadataDto, gameId);
        return new ResponseEntity<>(converterService.covertGameToGameDto(game), HttpStatus.OK);
    }
}
