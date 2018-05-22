package worldcup.Services.impls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import worldcup.Services.interfaces.BetsService;
import worldcup.Services.interfaces.TeamsService;
import worldcup.Services.interfaces.UsersService;
import worldcup.api.dtos.BetDto;
import worldcup.persistance.entities.Bet;
import worldcup.persistance.entities.Team;
import worldcup.persistance.entities.User;
import worldcup.persistance.repository.BetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BetsServiceImpl implements BetsService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private TeamsService teamsService;

    @Autowired
    private UsersService usersService;

    @Override
    public ArrayList<Bet> getAllBets() {
        return Lists.newArrayList(betRepository.findAll());
    }

    @Override
    public void validateBet(BetDto bet) {
        validateTeamNames(bet);
        validateBetDetails(bet);
    }

    @Override
    public void save(Bet bet) {
        betRepository.save(bet);
    }

    @Override
    public Bet findBetForUser() {
        User userFromDB = usersService.getUserFromDB();
        List<Bet> betsByUser = betRepository.findBetsByUser(userFromDB);
        if (betsByUser.isEmpty()){
            return null;
        }
        else{
            return betsByUser.get(0);
        }
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
}
