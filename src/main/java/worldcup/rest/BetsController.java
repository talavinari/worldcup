package worldcup.rest;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import worldcup.Services.TeamsService;
import worldcup.dtos.BetDto;
import worldcup.dtos.BetDtoResponse;
import worldcup.entities.Bet;
import worldcup.entities.Group;
import worldcup.entities.Team;
import worldcup.entities.User;
import worldcup.repository.BetRepository;
import worldcup.repository.GroupRepository;
import worldcup.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/bets")
public class BetsController {

    private static final Logger logger = LoggerFactory.getLogger(BetsController.class);

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeamsService teamsService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllBets() {
        Iterable<Bet> all = betRepository.findAll();
        ArrayList<Bet> bets = Lists.newArrayList(all);
        List<BetDtoResponse> betDtos = bets.stream().map
                (x -> new BetDtoResponse
                        (x.getRankA(), x.getRankB(), x.getRankC(), x.getRankD(), x.getBestScorer(),
                                x.getBestAttack(), x.getWorstDefence(), x.getUser().getName(),
                                x.getUser().getEmail(), x.getUser().getPoints(), x.getUser().getGroup().getName())).collect(Collectors.toList());
        return new ResponseEntity<>(betDtos, HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createNewBet(@RequestBody BetDto betRequest) {
        logger.info("New Bet Request arrived: {}", betRequest);

        validateBet(betRequest);

        Iterable<Group> all = groupRepository.findAll();
        Optional<Group> first = Lists.newArrayList(all).stream().filter(x -> x.getId().equals(betRequest.getGroupId())).findFirst();


        User newUser = new User(betRequest.getName(), betRequest.getEmail(), 0 , first.get() , null);
        userRepository.save(newUser);
        Bet bet = new Bet(
                betRequest.getRankA(),
                betRequest.getRankB(),
                betRequest.getRankC(),
                betRequest.getRankD(),
                betRequest.getBestScorer(),
                betRequest.getBestAttack(),
        betRequest.getWorstDefence(), newUser);
        betRepository.save(bet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateBet(BetDto bet) {
        validateUserDetails(bet);
        validateTeamNames(bet);
        validateBetDetails(bet);
    }

    private void validateTeamNames(BetDto bet) {
        ArrayList<Team> teams = teamsService.getAllTeams();
        Map<String, Set<Team>> groupedBy = teamsService.getTeamsByRank();
        if(!groupedBy.get("1").contains(new Team(bet.getRankA()))) {
            throw new RuntimeException(bet.getRankA() + " is NOT a Rank 1 team");
        }
        if(!groupedBy.get("2").contains(new Team(bet.getRankB()))) {
            throw new RuntimeException(bet.getRankA() + " is NOT a Rank 2 team");
        }
        if(!groupedBy.get("3").contains(new Team(bet.getRankC()))) {
            throw new RuntimeException(bet.getRankA() + " is NOT a Rank 3 team");
        }
        if(!groupedBy.get("4").contains(new Team(bet.getRankD()))) {
            throw new RuntimeException(bet.getRankA() + " is NOT a Rank 4 team");
        }

        if (!teams.contains(new Team(bet.getBestAttack()))) {
            throw new RuntimeException("Best attack team " + bet.getBestAttack() + " is NOT a valid team");
        }

        if (!teams.contains(new Team(bet.getWorstDefence()))) {
            throw new RuntimeException("Worst defence team " + bet.getWorstDefence() + " is NOT a valid team");
        }

        if (bet.getWorstDefence().equals(bet.getBestAttack())){
            throw new RuntimeException("Worst defence team and Best attack team must be different");
        }
    }

    private void validateBetDetails(BetDto bet) {

        if(StringUtils.isEmpty(bet.getRankA())) {
            throw new RuntimeException("Rank 1 team is missing");
        }
        if(StringUtils.isEmpty(bet.getRankB())) {
            throw new RuntimeException("Rank 2 team is missing");
        }
        if(StringUtils.isEmpty(bet.getRankC())) {
            throw new RuntimeException("Rank 3 team is missing");
        }
        if(StringUtils.isEmpty(bet.getRankD())) {
            throw new RuntimeException("Rank 4 team is missing");
        }
        if(StringUtils.isEmpty(bet.getBestScorer())) {
            throw new RuntimeException("Best Scorer is missing");
        }
        if(StringUtils.isEmpty(bet.getBestAttack())) {
            throw new RuntimeException("Best Attack is missing");
        }
        if(StringUtils.isEmpty(bet.getWorstDefence())) {
            throw new RuntimeException("Worst Defence is missing");
        }
    }

    private void validateUserDetails(BetDto bet) {
        if(StringUtils.isEmpty(bet.getName())) {
            throw new RuntimeException("User name is missing");
        }
        if(StringUtils.isEmpty(bet.getEmail())) {
            throw new RuntimeException("User's Email is missing");
        }
    }
}
