package worldcup.rest;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import worldcup.entities.Team;
import worldcup.repository.TeamRepository;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/teams")
public class TeamsController {

    private static final Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllTeams(@RequestParam(name = "rank", required = false) String rank) {
        Iterable<Team> all;
        if(StringUtils.isEmpty(rank)) {
            all = teamRepository.findAll();
        } else {
            all = teamRepository.findByRank(rank);
        }
        ArrayList<Team> bets = Lists.newArrayList(all);
        return new ResponseEntity<>(bets, HttpStatus.OK);
    }

}
