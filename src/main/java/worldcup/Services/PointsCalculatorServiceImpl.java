package worldcup.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.dtos.TeamGamesBalance;
import worldcup.dtos.TeamGoalsBalance;
import worldcup.entities.SoccerPlayer;
import worldcup.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointsCalculatorServiceImpl implements PointsCalculatorService {

    private final static Integer POINTS_FOR_TEAM_WIN = 3;
    private final static Integer POINTS_FOR_TEAM_WIN_AGAINST_BETTER_RANK_TEAM = 1;
    private final static Integer POINTS_FOR_TEAM_LOSS_AGAINST_LOWER_RANK_TEAM = -1;
    private final static Integer POINTS_FOR_TEAM_TIE = 1;
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

        List<String> bestAttack = null;
        List<String> worstDefense = null;
        //TODO select from DB if all group stage finished - handle points for worst + best teams
        if (gameService.isKnockOutStage()) {
            Map<String, TeamGoalsBalance> TeamGoalsBalanceMap = teamsService.calculateTeamGoalsBalance();
            bestAttack = teamsService.getBestAttack(TeamGoalsBalanceMap);
            worstDefense = teamsService.getWorstDefense(TeamGoalsBalanceMap);
        }

        Map<String, TeamGamesBalance> teamResults = teamsService.getTeamGamesBalance();
        Map<String, Integer> teamNameToPoints = calculateTeamPoints(teamResults);
        ArrayList<User> users = usersService.getAllUsers();
        Map<User, Integer> userToPoints = new HashMap<>();

        for (User user : users) {
            int userPoints = 0;
            userPoints += teamNameToPoints.get(user.getBet().getRankA());
            userPoints += teamNameToPoints.get(user.getBet().getRankB());
            userPoints += teamNameToPoints.get(user.getBet().getRankC());
            userPoints += teamNameToPoints.get(user.getBet().getRankD());
            userToPoints.put(user, userPoints);
        }

        //TODO if tournament finished add points for scorer. - calculate list of best
        List<SoccerPlayer> bestScorer = null;
        if (gameService.isTournamentFinished()) {
            bestScorer = soccerPlayersService.getBestScorer();
        }
        updateUsersPoints(bestAttack, worstDefense, bestScorer);
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

    private void updateUsersPoints(List<String> bestAttack, List<String> worstDefense, List<SoccerPlayer> bestScorer) {
        // TODO
        ArrayList<User> users = usersService.getAllUsers();

//        if (bestScorer != null && bestScorer.getNumberOfGoals() > 0) {
//
//        }
    }

}
