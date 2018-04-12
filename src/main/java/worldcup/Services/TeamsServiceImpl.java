package worldcup.Services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.entities.Team;
import worldcup.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@Service
public class TeamsServiceImpl implements  TeamsService
{
    @Autowired
    private TeamRepository teamRepository;

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
}
