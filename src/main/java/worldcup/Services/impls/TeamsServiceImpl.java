package worldcup.Services.impls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.Services.interfaces.GameService;
import worldcup.Services.interfaces.TeamsService;
import worldcup.api.dtos.TeamGamesBalance;
import worldcup.api.dtos.TeamGoalsBalance;
import worldcup.api.enums.GameStage;
import worldcup.persistance.entities.Game;
import worldcup.persistance.entities.Team;
import worldcup.persistance.repository.TeamRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@Service
public class TeamsServiceImpl implements TeamsService
{
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GameService gameService;

    @Override
    public ArrayList<Team> getAllTeams() {
        return Lists.newArrayList(teamRepository.findAll());
    }

    @Override
    public Map<String, Set<Team>> getTeamsByRank() {
        return getAllTeams()
                .stream()
                .collect(groupingBy(Team::getRank, toSet()));
    }

    @Override
    public Map<String,String> getTeamsToRank() {
        return  getAllTeams()
                .stream()
                .collect(Collectors.toMap(Team::getName, Team::getRank));
    }

    @Override
    public Map<String, TeamGamesBalance> getTeamGamesBalance() {
        Map<String, TeamGamesBalance> teamBalanceMap = new HashMap<>();

        Map<String, String> teamsToRank = getTeamsToRank();
        for (Game game : gameService.getFinishedGames()) {
            Integer team1Rank = Integer.valueOf(teamsToRank.get(game.getTeam1()));
            Integer team2Rank = Integer.valueOf(teamsToRank.get(game.getTeam2()));
            teamBalanceMap.putIfAbsent(game.getTeam1(), new TeamGamesBalance(game.getTeam1()));
            teamBalanceMap.putIfAbsent(game.getTeam2(), new TeamGamesBalance(game.getTeam2()));
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

    @Override
    public Map<String, TeamGoalsBalance> calculateTeamGoalsBalance() {
        Map<String, TeamGoalsBalance> calcMap = new HashMap<>();
        List<Game> groupGames = gameService.getGamesByStage(GameStage.Group);
        groupGames.forEach(game -> {
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

    @Override
    public List<String> getWorstDefense(Map<String, TeamGoalsBalance> teamToBalance) {
        List<TeamGoalsBalance> sortedTeams = teamToBalance
                .values()
                .stream()
                .sorted(Comparator.comparing(TeamGoalsBalance::getGoalsAgainst).reversed())
                .collect(Collectors.toList());
        if (sortedTeams.size() > 0) {
            Integer numberOfGoalsAgainst = sortedTeams.get(0).getGoalsAgainst();
            return sortedTeams
                    .stream()
                    .filter(x->x.getGoalsAgainst().equals(numberOfGoalsAgainst))
                    .map(x->x.getTeamName())
                    .collect(Collectors.toList());
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public List<String> getBestAttack(Map<String, TeamGoalsBalance> teamToBalance) {
        List<TeamGoalsBalance> sortedTeams = teamToBalance
                .values()
                .stream()
                .sorted(Comparator.comparing(TeamGoalsBalance::getGoalsFor).reversed())
                .collect(Collectors.toList());
        if (sortedTeams.size() > 0) {
            Integer numberOfGoalsFor = sortedTeams.get(0).getGoalsFor();
            return sortedTeams
                    .stream()
                    .filter(x->x.getGoalsFor().equals(numberOfGoalsFor))
                    .map(x->x.getTeamName())
                    .collect(Collectors.toList());
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public ArrayList<Team> findByRank(String rank) {
        return Lists.newArrayList(teamRepository.findByRank(rank));
    }
}
