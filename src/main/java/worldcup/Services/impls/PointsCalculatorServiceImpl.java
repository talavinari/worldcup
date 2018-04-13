package worldcup.Services.impls;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.Services.interfaces.*;
import worldcup.api.dtos.TeamGamesBalance;
import worldcup.api.dtos.TeamGoalsBalance;
import worldcup.persistance.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PointsCalculatorServiceImpl implements PointsCalculatorService {

    private final static Integer POINTS_FOR_TEAM_WIN = 3;
    private final static Integer POINTS_FOR_TEAM_WIN_AGAINST_BETTER_RANK_TEAM = 1;
    private final static Integer POINTS_FOR_TEAM_LOSS_AGAINST_LOWER_RANK_TEAM = -1;
    private final static Integer POINTS_FOR_TEAM_TIE = 1;
    private final static Integer POINTS_FOR_MOVING_TO_NEXT_STAGE = 3;
    private final static Integer POINTS_FOR_BEST_SCORER = 5;
    private final static Integer POINTS_FOR_BEST_ATTACK_TEAM = 5;
    private final static Integer POINTS_FOR_WORST_DEFENSE_TEAM = 5;

    @Autowired
    private UsersService usersService;

    @Autowired
    private SoccerPlayersService soccerPlayersService;

    @Autowired
    private TeamsService teamsService;

    @Autowired
    private GameService gameService;

    @Override
    public void calculateUsersPoint() {

        Set<String> bestAttacks = Sets.newHashSet();
        Set<String> worstDefenses = Sets.newHashSet();
        //TODO select from DB if all group stage finished - handle points for worst + best teams
        if (gameService.isKnockOutStage()) {
            Map<String, TeamGoalsBalance> TeamGoalsBalanceMap = teamsService.calculateTeamGoalsBalance();
            bestAttacks = teamsService.getBestAttack(TeamGoalsBalanceMap)
                    .stream().collect(Collectors.toSet());
            worstDefenses = teamsService.getWorstDefense(TeamGoalsBalanceMap)
                    .stream().collect(Collectors.toSet());
        }

        Map<String, TeamGamesBalance> teamResults = teamsService.getTeamGamesBalance();
        Map<String, Integer> teamNameToPoints = calculateTeamPoints(teamResults);

        //TODO if tournament finished add points for scorer. - calculate list of best
        Set<String> bestScorers = Sets.newHashSet();
        if (gameService.isTournamentFinished()) {
            bestScorers =
                    soccerPlayersService.getBestScorer()
                            .stream()
                            .map(x->x.getName()).collect(Collectors.toSet());
        }
        updateUsersPoints(teamNameToPoints, bestAttacks, worstDefenses, bestScorers);
    }

    private Map<String, Integer> calculateTeamPoints(Map<String, TeamGamesBalance> teamResults) {
        Map<String, Integer> map = new HashMap<>();

        for (Map.Entry<String, TeamGamesBalance> entry : teamResults.entrySet()) {
            map.put(entry.getKey(), calcPointsForBalance(entry.getValue()));
        }

        return map;
    }

    private Integer calcPointsForBalance(TeamGamesBalance value) {
        int points = 0;
        points += value.getTotalWins() * POINTS_FOR_TEAM_WIN;
        points += value.getWinsAgaintBetterRankTeam() * POINTS_FOR_TEAM_WIN_AGAINST_BETTER_RANK_TEAM;
        points += value.getTies() * POINTS_FOR_TEAM_TIE;
        points += value.getLosesAgainstLowerRankTeam() * POINTS_FOR_TEAM_LOSS_AGAINST_LOWER_RANK_TEAM;
        return points;
    }

    private void updateUsersPoints(Map<String, Integer> teamNameToPoints, Set<String> bestAttacks, Set<String> worstDefenses, Set<String> bestScorers) {
        ArrayList<User> users = usersService.getAllUsers();
        Map<User, Integer> userToPoints = new HashMap<>();

        users
                .forEach(user-> {
                    int userPoints = 0;
                    // General wins/loses/ties calculated by rank
                    userPoints += teamNameToPoints.get(user.getBet().getRankA());
                    userPoints += teamNameToPoints.get(user.getBet().getRankB());
                    userPoints += teamNameToPoints.get(user.getBet().getRankC());
                    userPoints += teamNameToPoints.get(user.getBet().getRankD());

                    // Best Scorer
                    String bestScorer = user.getBet().getBestScorer();
                    if(bestScorer !=null &&
                            bestScorers.contains(bestScorer)) {
                        userPoints+=POINTS_FOR_BEST_SCORER;
                    }

                    //Worst Defense
                    String worstDefence = user.getBet().getWorstDefence();
                    if(worstDefence!=null &&
                            worstDefenses.contains(worstDefence)) {
                        userPoints+=POINTS_FOR_WORST_DEFENSE_TEAM;
                    }

                    // Best Attack
                    String bestAttack = user.getBet().getBestAttack();
                    if(bestAttack!=null &&
                            bestAttacks.contains(bestAttack)) {
                        userPoints+=POINTS_FOR_BEST_ATTACK_TEAM;
                    }

                    userToPoints.put(user, userPoints);
                });

        // Save users with the newly calculated points
        userToPoints.entrySet()
                .stream()
                .forEach(x-> {
                    User user = x.getKey();
                    user.setPoints(x.getValue());
                    usersService.save(user);
                });
    }

}
