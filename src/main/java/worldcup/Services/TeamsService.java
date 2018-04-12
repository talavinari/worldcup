package worldcup.Services;

import worldcup.entities.Team;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface TeamsService {
    ArrayList<Team> getAllTeams();
    Map<String, Set<Team>> getTeamsByRank();

    Map<String,String> getTeamsToRank();
}
