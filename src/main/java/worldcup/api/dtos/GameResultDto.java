package worldcup.api.dtos;

import java.util.List;

public class GameResultDto {

    private Long id;
    private String team1;
    private String team2;
    private Integer score1;
    private Integer score2;
    private List<ScorerDto> soccerPlayers;

    public GameResultDto() {
    }

    public GameResultDto(Long id, String team1, String team2, Integer score1, Integer score2, List<ScorerDto> soccerPlayers) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.soccerPlayers = soccerPlayers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public List<ScorerDto> getSoccerPlayers() {
        return soccerPlayers;
    }

    public void setSoccerPlayers(List<ScorerDto> soccerPlayers) {
        this.soccerPlayers = soccerPlayers;
    }

    @Override
    public String toString() {
        return "GameResultDto{" +
                "id=" + id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", soccerPlayers=" + soccerPlayers +
                '}';
    }
}
