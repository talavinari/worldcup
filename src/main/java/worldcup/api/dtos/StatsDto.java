package worldcup.api.dtos;

import java.util.List;

public class StatsDto {
    private SoccerPlayersStatsDto soccerPlayersStats;
    private List<TeamGamesBalance> teamGamesBalances;
    private List<TeamGoalsBalance> teamGoalsBalances;

    public SoccerPlayersStatsDto getSoccerPlayersStats() {
        return soccerPlayersStats;
    }

    public void setSoccerPlayersStats(SoccerPlayersStatsDto soccerPlayersStats) {
        this.soccerPlayersStats = soccerPlayersStats;
    }

    public List<TeamGamesBalance> getTeamGamesBalances() {
        return teamGamesBalances;
    }

    public void setTeamGamesBalances(List<TeamGamesBalance> teamGamesBalances) {
        this.teamGamesBalances = teamGamesBalances;
    }

    public List<TeamGoalsBalance> getTeamGoalsBalances() {
        return teamGoalsBalances;
    }

    public void setTeamGoalsBalances(List<TeamGoalsBalance> teamGoalsBalances) {
        this.teamGoalsBalances = teamGoalsBalances;
    }

    @Override
    public String toString() {
        return "StatsDto{" +
                "soccerPlayersStats=" + soccerPlayersStats +
                ", teamGamesBalances=" + teamGamesBalances +
                ", teamGoalsBalances=" + teamGoalsBalances +
                '}';
    }
}
