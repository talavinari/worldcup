package worldcup.Services.impls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.Services.interfaces.SoccerPlayersService;
import worldcup.Services.interfaces.StatsService;
import worldcup.Services.interfaces.TeamsService;
import worldcup.api.dtos.StatsDto;
import worldcup.api.dtos.TeamGamesBalance;
import worldcup.api.dtos.TeamGoalsBalance;

import java.util.Map;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private SoccerPlayersService soccerPlayersService;

    @Autowired
    private TeamsService teamsService;

    @Override
    public StatsDto getStats() {
        StatsDto statsDto = new StatsDto();
        Map<String, TeamGamesBalance> teamGamesBalance = teamsService.getTeamGamesBalance();
        Map<String, TeamGoalsBalance> teamGoalsBalance = teamsService.calculateTeamGoalsBalance();
        statsDto.setSoccerPlayersStats(soccerPlayersService.getSoccerPlayersStats());
        statsDto.setTeamGamesBalances(Lists.newArrayList(teamGamesBalance.values()));
        statsDto.setTeamGoalsBalances(Lists.newArrayList(teamGoalsBalance.values()));
        return statsDto;
    }
}
