package worldcup.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worldcup.Services.interfaces.BetsService;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.GroupService;
import worldcup.Services.interfaces.UsersService;
import worldcup.api.dtos.BetDto;
import worldcup.api.dtos.BetDtoResponse;
import worldcup.persistance.entities.Bet;
import worldcup.persistance.entities.Group;
import worldcup.persistance.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/bets")
public class BetsController {

    private static final Logger logger = LoggerFactory.getLogger(BetsController.class);

    @Autowired
    private BetsService betsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ConverterService converterService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllBets() {
        ArrayList<Bet> bets = betsService.getAllBets();
        List<BetDtoResponse> betDtos = bets.stream().map
                (x -> converterService.covertBetToBetDtoResponse(x)).collect(Collectors.toList());
        return new ResponseEntity<>(betDtos, HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createNewBet(@RequestBody BetDto betRequest) {
        logger.info("New Bet Request arrived: {}", betRequest);

        betsService.validateBet(betRequest);

        Optional<Group> first = groupService.findAll().stream().filter(x -> x.getId().equals(betRequest.getGroupId())).findFirst();

        User newUser = new User(betRequest.getName(), betRequest.getEmail(), 0 , first.get() , null);
        usersService.save(newUser);
        Bet bet = converterService.covertBetDtoToBet(betRequest, newUser);
        betsService.save(bet);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
