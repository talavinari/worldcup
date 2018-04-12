package worldcup.Services;

import worldcup.dtos.TeamGamesBalance;
import worldcup.dtos.TeamGoalsBalance;
import worldcup.entities.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TeamsService {
    ArrayList<Team> getAllTeams();
    Map<String, Set<Team>> getTeamsByRank();
    Map<String,String> getTeamsToRank();
    Map<String, TeamGamesBalance> getTeamGamesBalance();
    Map<String, TeamGoalsBalance> calculateTeamGoalsBalance();
    List<String> getWorstDefense(Map<String, TeamGoalsBalance> teamToBalance);
    List<String> getBestAttack(Map<String, TeamGoalsBalance> teamToBalance);
    ArrayList<Team> findByRank(String rank);
}
