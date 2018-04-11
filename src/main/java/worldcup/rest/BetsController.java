package worldcup.rest;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worldcup.dtos.BetDto;
import worldcup.dtos.BetDtoResponse;
import worldcup.entities.Bet;
import worldcup.entities.Group;
import worldcup.entities.User;
import worldcup.repository.BetRepository;
import worldcup.repository.GroupRepository;
import worldcup.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


    @RequestMapping(method = RequestMethod.GET, path = "/")
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
}
