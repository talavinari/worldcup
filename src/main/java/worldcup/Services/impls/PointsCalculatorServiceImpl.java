package worldcup.Services.impls;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.Services.enums.GameStage;
import worldcup.Services.interfaces.*;
import worldcup.api.dtos.TeamGamesBalance;
import worldcup.api.dtos.TeamGoalsBalance;
import worldcup.persistance.entities.Game;
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
    private final static Integer POINTS_FOR_TURNAMENT_WINNER_TEAM_BONUS = 2;

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

        Map<GameStage, List<String>> teamsByStages = gameService.getTeamsByStages();

        Set<String> bestScorers = Sets.newHashSet();
        if (gameService.isTournamentFinished()) {
            bestScorers =
                    soccerPlayersService.getBestScorer()
                            .stream()
                            .map(SoccerPlayer::getName).
                            collect(Collectors.toSet());
        }
        updateUsersPoints(teamsByStages, bestAttacks, worstDefenses, bestScorers);
    }

    @Override
    public Map<String, Integer> getTeamToPointsMap() {
        Map<String, TeamGamesBalance> teamResults = teamsService.getTeamGamesBalance();
        Map<String, Integer> map = new HashMap<>();

        for (Map.Entry<String, TeamGamesBalance> entry : teamResults.entrySet()) {
            map.put(entry.getKey(), calcPointsForBalance(entry.getValue()));
        }

        teamsService.getAllTeams().
                forEach(x-> map.putIfAbsent(x.getName(), 0));
        return map;
    }

    private Integer calcPointsForBalance(TeamGamesBalance teamGamesBalance) {
        int points = 0;
        points += teamGamesBalance.getTotalWins() * POINTS_FOR_TEAM_WIN;
        points += teamGamesBalance.getWinsAgaintBetterRankTeam() * POINTS_FOR_TEAM_WIN_AGAINST_BETTER_RANK_TEAM;
        points += teamGamesBalance.getTies() * POINTS_FOR_TEAM_TIE;
        points += teamGamesBalance.getLosesAgainstLowerRankTeam() * POINTS_FOR_TEAM_LOSS_AGAINST_LOWER_RANK_TEAM;
        Optional<String> winner = findWinner();
        if (winner.isPresent() && teamGamesBalance.getTeamName().equals(winner.get())){
            points += POINTS_FOR_TURNAMENT_WINNER_TEAM_BONUS;
        }
        return points;
    }

    private Optional<String> findWinner() {
        List<Game> gamesByStage = gameService.getGamesByStage(GameStage.FINAL);
        Game finalGame = gamesByStage.get(0);
        if (!gameService.isGameFinished(finalGame)) {
            return Optional.empty();
        }
        Optional<Integer> extraTimeScore1 = Optional.ofNullable(finalGame.getExtraTimeScore1());
        Optional<Integer> extraTimeScore2 = Optional.ofNullable(finalGame.getExtraTimeScore2());
        Optional<Integer> penaltyScore1 = Optional.ofNullable(finalGame.getPenaltyScore1());
        Optional<Integer> penaltyScore2 = Optional.ofNullable(finalGame.getPenaltyScore2());

        int scoreA = finalGame.getScore1() + extraTimeScore1.orElse(0) + penaltyScore1.orElse(0);
        int scoreB = finalGame.getScore2() + extraTimeScore2.orElse(0) + penaltyScore2.orElse(0);

        return scoreA > scoreB ? Optional.of(finalGame.getTeam1()) : Optional.of(finalGame.getTeam2());
    }

    private void updateUsersPoints(Map<GameStage, List<String>> teamsByStages, Set<String> bestAttacks, Set<String> worstDefenses, Set<String> bestScorers) {
        Map<String, Integer> teamNameToPoints = getTeamToPointsMap();
        ArrayList<User> users = usersService.getAllUsers();
        Map<User, Integer> userToPoints = new HashMap<>();

        users
                .forEach(user -> {
                    final int[] userPoints = {0};
                    // General wins/loses/ties calculated by rank
                    userPoints[0] += teamNameToPoints.get(user.getBet().getRankA());
                    userPoints[0] += teamNameToPoints.get(user.getBet().getRankB());
                    userPoints[0] += teamNameToPoints.get(user.getBet().getRankC());
                    userPoints[0] += teamNameToPoints.get(user.getBet().getRankD());

                    // Teams that passed to next level
                    Arrays.asList(GameStage.values())
                            .stream()
                            .filter(stage -> !stage.equals(GameStage.Group))
                            .filter(stage -> !stage.equals(GameStage.THIRD_PLACE_PLAY_OFF))
                            .forEach(stage -> {
                                if (teamsByStages.get(stage)
                                        .contains(user.getBet().getRankA())) {
                                    userPoints[0] += POINTS_FOR_MOVING_TO_NEXT_STAGE;
                                }
                                if (teamsByStages.get(stage)
                                        .contains(user.getBet().getRankB())) {
                                    userPoints[0] += POINTS_FOR_MOVING_TO_NEXT_STAGE;
                                }
                                if (teamsByStages.get(stage)
                                        .contains(user.getBet().getRankC())) {
                                    userPoints[0] += POINTS_FOR_MOVING_TO_NEXT_STAGE;
                                }
                                if (teamsByStages.get(stage)
                                        .contains(user.getBet().getRankD())) {
                                    userPoints[0] += POINTS_FOR_MOVING_TO_NEXT_STAGE;
                                }
                            });

                    // Best Scorer
                    String bestScorer = user.getBet().getBestScorer();
                    if (bestScorer != null &&
                            bestScorers.contains(bestScorer)) {
                        userPoints[0] += POINTS_FOR_BEST_SCORER;
                    }

                    //Worst Defense
                    String worstDefence = user.getBet().getWorstDefence();
                    if (worstDefence != null &&
                            worstDefenses.contains(worstDefence)) {
                        userPoints[0] += POINTS_FOR_WORST_DEFENSE_TEAM;
                    }

                    // Best Attack
                    String bestAttack = user.getBet().getBestAttack();
                    if (bestAttack != null &&
                            bestAttacks.contains(bestAttack)) {
                        userPoints[0] += POINTS_FOR_BEST_ATTACK_TEAM;
                    }

                    userToPoints.put(user, userPoints[0]);
                });

        // Save users with the newly calculated points
        userToPoints.entrySet()
                .stream()
                .forEach(x -> {
                    User user = x.getKey();
                    user.setPoints(x.getValue());
                    usersService.save(user);
                });
    }

}
