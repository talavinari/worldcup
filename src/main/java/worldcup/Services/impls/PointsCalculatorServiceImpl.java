package worldcup.Services.impls;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.Services.enums.GameStage;
import worldcup.Services.interfaces.*;
import worldcup.api.dtos.TeamGamesBalance;
import worldcup.api.dtos.TeamGoalsBalance;
import worldcup.persistance.entities.SoccerPlayer;
import worldcup.persistance.entities.User;

import java.util.*;
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
        if (gameService.isKnockOutStage()) {
            Map<String, TeamGoalsBalance> TeamGoalsBalanceMap = teamsService.calculateTeamGoalsBalance();
            bestAttacks = new HashSet<>(teamsService.getBestAttack(TeamGoalsBalanceMap));
            worstDefenses = new HashSet<>(teamsService.getWorstDefense(TeamGoalsBalanceMap));
        }

        Map<String, TeamGamesBalance> teamResults = teamsService.getTeamGamesBalance();
        Map<String, Integer> teamNameToPoints = calculateTeamPoints(teamResults);

        Map<GameStage, List<String>> teamsByStages = gameService.getTeamsByStages();

        Set<String> bestScorers = Sets.newHashSet();
        if (gameService.isTournamentFinished()) {
            bestScorers =
                    soccerPlayersService.getBestScorer()
                            .stream()
                            .map(SoccerPlayer::getName).
                            collect(Collectors.toSet());
        }
        updateUsersPoints(teamNameToPoints, teamsByStages, bestAttacks, worstDefenses, bestScorers);
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

    private void updateUsersPoints(Map<String, Integer> teamNameToPoints, Map<GameStage, List<String>> teamsByStages, Set<String> bestAttacks, Set<String> worstDefenses, Set<String> bestScorers) {
        ArrayList<User> users = usersService.getAllUsers();
        Map<User, Integer> userToPoints = new HashMap<>();

        users
                .forEach(user-> {
                    final int[] userPoints = {0};
                    // General wins/loses/ties calculated by rank
                    userPoints[0] += teamNameToPoints.get(user.getBet().getRankA());
                    userPoints[0] += teamNameToPoints.get(user.getBet().getRankB());
                    userPoints[0] += teamNameToPoints.get(user.getBet().getRankC());
                    userPoints[0] += teamNameToPoints.get(user.getBet().getRankD());

                    // Teams that passed to next level
                    Arrays.asList(GameStage.values())
                            .stream()
                            .filter(stage->!stage.equals(GameStage.Group))
                            .filter(stage->!stage.equals(GameStage.THIRD_PLACE_PLAY_OFF))
                            .forEach(stage -> {
                                if(teamsByStages.get(stage)
                                        .contains(user.getBet().getRankA())) {
                                    userPoints[0] +=POINTS_FOR_MOVING_TO_NEXT_STAGE;
                                }
                                if(teamsByStages.get(stage)
                                        .contains(user.getBet().getRankB())) {
                                    userPoints[0] +=POINTS_FOR_MOVING_TO_NEXT_STAGE;
                                }
                                if(teamsByStages.get(stage)
                                        .contains(user.getBet().getRankC())) {
                                    userPoints[0] +=POINTS_FOR_MOVING_TO_NEXT_STAGE;
                                }
                                if(teamsByStages.get(stage)
                                        .contains(user.getBet().getRankD())) {
                                    userPoints[0] +=POINTS_FOR_MOVING_TO_NEXT_STAGE;
                                }
                            });

                    // Best Scorer
                    String bestScorer = user.getBet().getBestScorer();
                    if(bestScorer !=null &&
                            bestScorers.contains(bestScorer)) {
                        userPoints[0] +=POINTS_FOR_BEST_SCORER;
                    }

                    //Worst Defense
                    String worstDefence = user.getBet().getWorstDefence();
                    if(worstDefence!=null &&
                            worstDefenses.contains(worstDefence)) {
                        userPoints[0] +=POINTS_FOR_WORST_DEFENSE_TEAM;
                    }

                    // Best Attack
                    String bestAttack = user.getBet().getBestAttack();
                    if(bestAttack!=null &&
                            bestAttacks.contains(bestAttack)) {
                        userPoints[0] +=POINTS_FOR_BEST_ATTACK_TEAM;
                    }

                    userToPoints.put(user, userPoints[0]);
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
