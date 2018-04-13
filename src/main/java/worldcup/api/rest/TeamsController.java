package worldcup.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.TeamsService;
import worldcup.persistance.entities.Team;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/teams")
public class TeamsController {

    private static final Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @Autowired
    private TeamsService teamsService;

    @Autowired
    private ConverterService converterService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllTeams(@RequestParam(name = "rank", required = false) String rank) {
        ArrayList<Team> teams;
        if(StringUtils.isEmpty(rank)) {
            teams = teamsService.getAllTeams();
        } else {
            teams = teamsService.findByRank(rank);
        }
        return new ResponseEntity<>(converterService.convertTeamsToTeamsDto(teams), HttpStatus.OK);
    }

}
