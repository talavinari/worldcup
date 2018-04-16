package worldcup.api.dtos;

import java.util.List;

public class GameResultDto {

    private Long id;
    private String team1;
    private String team2;
    private Integer score1;
    private Integer score2;
    private Integer extraTimeScore1;
    private Integer extraTimeScore2;
    private Integer penaltyScore1;
    private Integer penaltyScore2;
    private List<ScorerDto> soccerPlayers;

    public GameResultDto() {
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
                ", extraTimeScore1=" + extraTimeScore1 +
                ", extraTimeScore2=" + extraTimeScore2 +
                ", penaltyScore1=" + penaltyScore1 +
                ", penaltyScore2=" + penaltyScore2 +
                ", soccerPlayers=" + soccerPlayers +
                '}';
    }

    public Integer getExtraTimeScore1() {
        return extraTimeScore1;
    }

    public void setExtraTimeScore1(Integer extraTimeScore1) {
        this.extraTimeScore1 = extraTimeScore1;
    }

    public Integer getExtraTimeScore2() {
        return extraTimeScore2;
    }

    public void setExtraTimeScore2(Integer extraTimeScore2) {
        this.extraTimeScore2 = extraTimeScore2;
    }

    public Integer getPenaltyScore1() {
        return penaltyScore1;
    }

    public void setPenaltyScore1(Integer penaltyScore1) {
        this.penaltyScore1 = penaltyScore1;
    }

    public Integer getPenaltyScore2() {
        return penaltyScore2;
    }

    public void setPenaltyScore2(Integer penaltyScore2) {
        this.penaltyScore2 = penaltyScore2;
    }
}
