package worldcup.Services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.dtos.TeamGamesBalance;
import worldcup.dtos.TeamGoalsBalance;
import worldcup.entities.Game;
import worldcup.entities.SoccerPlayer;
import worldcup.entities.Team;
import worldcup.entities.User;
import worldcup.repository.SoccerPlayerRepository;
import worldcup.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

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
    private UserRepository userRepository;

    @Autowired
    private SoccerPlayerRepository soccerPlayerRepository;

    @Autowired
    private TeamsService teamsService;

    @Autowired
    private GameService gameService;

    @Override
    public void calculateUsersPoint() {

        Team bestAttack = null;
        Team worstDefense = null;
        //TODO select from DB if all group stage finished - handle points for worst + best teams
        if (gameService.isKnockOutStage()) {
            Map<String, TeamGoalsBalance> stringTeamGoalsBalanceMap = calculateTeamGoals();
            bestAttack = getBestAttack(stringTeamGoalsBalanceMap);
            worstDefense = getWorstDefense(stringTeamGoalsBalanceMap);
        }

        Map<String, TeamGamesBalance> teamResults = getTeamResults();
        Map<String, Integer> teamNameToPoints = calculateTeamPoints(teamResults);
        ArrayList<User> users = Lists.newArrayList(userRepository.findAll());
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
        SoccerPlayer bestScorer = null;
        if (isTournamentFinished()) {
            bestScorer = getBestScorer();
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


    private boolean isTournamentFinished() {
        return false;
    }

    private boolean isKnokOutStage() {
        return false;
    }

    private Map<String, TeamGoalsBalance> calculateTeamGoals() {
        Map<String, TeamGoalsBalance> calcMap = new HashMap<>();
        List<Game> finishedGames = gameService.getFinishedGames();
        finishedGames.forEach(game -> {
            String team1 = game.getTeam1();
            String team2 = game.getTeam2();

            calcMap.putIfAbsent(team1, new TeamGoalsBalance(team1, 0, 0));
            calcMap.putIfAbsent(team2, new TeamGoalsBalance(team2, 0, 0));
                    TeamGoalsBalance firstTeamCalc = calcMap.get(team1);
                    firstTeamCalc.setGoalsFor(firstTeamCalc.getGoalsFor() + game.getScore1());
                    firstTeamCalc.setGoalsAgainst(firstTeamCalc.getGoalsAgainst() + game.getScore2());
                    TeamGoalsBalance secondTeamCalc = calcMap.get(team2);
                    secondTeamCalc.setGoalsFor(secondTeamCalc.getGoalsFor() + game.getScore2());
                    secondTeamCalc.setGoalsAgainst(secondTeamCalc.getGoalsAgainst() + game.getScore1());
                }
        );
        return calcMap;
    }


    private Map<String, TeamGamesBalance> getTeamResults() {
        Map<String, TeamGamesBalance> teamBalanceMap = new HashMap<>();

        Map<String, String> teamsToRank = teamsService.getTeamsToRank();
        for (Game game : gameService.getFinishedGames()) {
            Integer team1Rank = Integer.valueOf(teamsToRank.get(game.getTeam1()));
            Integer team2Rank = Integer.valueOf(teamsToRank.get(game.getTeam2()));
            teamBalanceMap.putIfAbsent(game.getTeam1(), new TeamGamesBalance());
            teamBalanceMap.putIfAbsent(game.getTeam2(), new TeamGamesBalance());
            if (game.getScore1().equals(game.getScore2())) {
                teamBalanceMap.get(game.getTeam1()).incrementTie();
                teamBalanceMap.get(game.getTeam2()).incrementTie();
            } else if (game.getScore1().compareTo(game.getScore2()) > 0) {
                teamBalanceMap.get(game.getTeam1()).incrementTotalWin();
                teamBalanceMap.get(game.getTeam2()).incrementTotalLoses();
                if (team1Rank > team2Rank) {
                    teamBalanceMap.get(game.getTeam1()).incrementWinsAgainstBetterRankTeam();
                    teamBalanceMap.get(game.getTeam2()).incrementLosesAgainstLowerRankTeam();
                }
            } else {
                teamBalanceMap.get(game.getTeam2()).incrementTotalWin();
                teamBalanceMap.get(game.getTeam1()).incrementTotalLoses();
                if (team2Rank > team1Rank) {
                    teamBalanceMap.get(game.getTeam2()).incrementWinsAgainstBetterRankTeam();
                    teamBalanceMap.get(game.getTeam1()).incrementLosesAgainstLowerRankTeam();
                }
            }
        }


        return teamBalanceMap;
    }

    private SoccerPlayer getBestScorer() {
        List<SoccerPlayer> soccerPlayers =
                Lists.newArrayList(soccerPlayerRepository.findAll())
                        .stream()
                        .sorted(Comparator.comparing(SoccerPlayer::getNumberOfGoals).reversed())
                        .collect(Collectors.toList());
        if (soccerPlayers.size() > 0) {
            return soccerPlayers.get(0);
        } else {
            return null;
        }
    }

    private Team getWorstDefense(Map<String, TeamGoalsBalance> stringTeamGoalsBalanceMap) {
        // TODO - list of worst
        return null;
    }

    private Team getBestAttack(Map<String, TeamGoalsBalance> teamToBalance) {
        teamToBalance.entrySet().stream().map(x -> x.getValue());
        // TODO - list of best
        return null;
    }

    private void updateUsersPoints(Team bestAttack, Team worstDefense, SoccerPlayer bestScorer) {
        // TODO
        ArrayList<User> users = Lists.newArrayList(userRepository.findAll());

        if (bestScorer != null && bestScorer.getNumberOfGoals() > 0) {

        }
    }

}
