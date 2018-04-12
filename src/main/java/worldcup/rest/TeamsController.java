package worldcup.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import worldcup.Services.TeamsService;
import worldcup.entities.Team;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/teams")
public class TeamsController {

    private static final Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @Autowired
    private TeamsService teamsService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllTeams(@RequestParam(name = "rank", required = false) String rank) {
        ArrayList<Team> bets;
        if(StringUtils.isEmpty(rank)) {
            bets = teamsService.getAllTeams();
        } else {
            bets = teamsService.findByRank(rank);
        }
        return new ResponseEntity<>(bets, HttpStatus.OK);
    }

}
