package worldcup.api.dtos;

import java.util.List;
import java.util.Objects;

public class SoccerPlayersStatsDto {
    private List<SoccerPlayerStatDto> soccerPlayersStats;

    public SoccerPlayersStatsDto() {
    }

    public SoccerPlayersStatsDto(List<SoccerPlayerStatDto> soccerPlayersStats) {
        this.soccerPlayersStats = soccerPlayersStats;
    }

    public List<SoccerPlayerStatDto> getSoccerPlayersStats() {
        return soccerPlayersStats;
    }

    public void setSoccerPlayersStats(List<SoccerPlayerStatDto> soccerPlayersStats) {
        this.soccerPlayersStats = soccerPlayersStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoccerPlayersStatsDto that = (SoccerPlayersStatsDto) o;
        return Objects.equals(soccerPlayersStats, that.soccerPlayersStats);
    }

    @Override
    public int hashCode() {

        return Objects.hash(soccerPlayersStats);
    }

    @Override
    public String toString() {
        return "SoccerPlayersStatsDto{" +
                "soccerPlayersStats=" + soccerPlayersStats +
                '}';
    }
}
