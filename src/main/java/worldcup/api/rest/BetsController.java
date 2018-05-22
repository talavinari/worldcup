package worldcup.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worldcup.Services.Person;
import worldcup.Services.interfaces.BetsService;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.PointsCalculatorService;
import worldcup.Services.interfaces.UsersService;
import worldcup.api.dtos.BetDto;
import worldcup.api.dtos.BetDtoResponse;
import worldcup.persistance.entities.Bet;
import worldcup.persistance.entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    private ConverterService converterService;

    @Autowired
    private PointsCalculatorService pointsCalculatorService;

    @Value("${mail.suffix}")
    private String mailSuffix;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllBets() {
        Map<String, Integer> teamToPointsMap = pointsCalculatorService.getTeamToPointsMap();
        ArrayList<Bet> bets = betsService.getAllBets();

        List<BetDtoResponse> betDtos = bets.stream().map
                (x -> converterService.covertBetToBetDtoResponse(x, teamToPointsMap)).
                sorted(Comparator.comparing(BetDtoResponse::getPoints).reversed()).
                collect(Collectors.toList());
        return new ResponseEntity<>(betDtos, HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> createNewBet(@RequestBody BetDto betRequest) {
        logger.info("New Bet Request arrived: {}", betRequest);

        betsService.validateBet(betRequest);
        Person person = usersService.getCurrentLoggedInUserLdapData();

        User user;
        Bet bet;
        Bet existingBetForUser = betsService.findBetForUser();
        if (existingBetForUser == null) {
            user = new User(person.getName(),
                    usersService.getCurrentLoggedInUserName() + "@" + mailSuffix,
                    0, person.getDepartment(), null);
            usersService.save(user);
            bet = converterService.covertBetDtoToBet
                    (betRequest, user);
        } else {
            bet = existingBetForUser;
            bet.setRankA(betRequest.getRankA());
            bet.setRankB(betRequest.getRankB());
            bet.setRankC(betRequest.getRankC());
            bet.setRankD(betRequest.getRankD());
            bet.setBestScorer(betRequest.getBestScorer());
            bet.setBestAttack(betRequest.getBestAttack());
            bet.setWorstDefence(betRequest.getWorstDefence());
        }

        betsService.save(bet);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
