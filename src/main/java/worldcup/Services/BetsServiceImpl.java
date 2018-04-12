package worldcup.Services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import worldcup.dtos.BetDto;
import worldcup.entities.Bet;
import worldcup.entities.Team;
import worldcup.repository.BetRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@Service
public class BetsServiceImpl implements BetsService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private TeamsService teamsService;

    @Override
    public ArrayList<Bet> getAllBets() {
        return Lists.newArrayList(betRepository.findAll());
    }

    @Override
    public void validateBet(BetDto bet) {
        validateUserDetails(bet);
        validateTeamNames(bet);
        validateBetDetails(bet);
    }

    @Override
    public void save(Bet bet) {
        betRepository.save(bet);
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
