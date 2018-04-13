package worldcup.api.dtos;

import java.util.Date;
import java.util.Objects;

public class GameDto {

    private Long id;
    private String team1;
    private String team2;
    private Integer score1;
    private Integer score2;
    private Date gameTime;
    private String stage;
    private Boolean shouldOverride;

    public GameDto() {
    }

    public GameDto(Long id, String team1, String team2, Integer score1, Integer score2, Date gameTime, String stage, Boolean shouldOverride) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.gameTime = gameTime;
        this.stage = stage;
        this.shouldOverride = shouldOverride;
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

    public Date getGameTime() {
        return gameTime;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Boolean getShouldOverride() {
        return shouldOverride;
    }

    public void setShouldOverride(Boolean shouldOverride) {
        this.shouldOverride = shouldOverride;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDto gameDto = (GameDto) o;
        return Objects.equals(id, gameDto.id) &&
                Objects.equals(team1, gameDto.team1) &&
                Objects.equals(team2, gameDto.team2) &&
                Objects.equals(score1, gameDto.score1) &&
                Objects.equals(score2, gameDto.score2) &&
                Objects.equals(gameTime, gameDto.gameTime) &&
                Objects.equals(stage, gameDto.stage) &&
                Objects.equals(shouldOverride, gameDto.shouldOverride);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, team1, team2, score1, score2, gameTime, stage, shouldOverride);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", gameTime=" + gameTime +
                ", stage='" + stage + '\'' +
                ", shouldOverride=" + shouldOverride +
                '}';
    }
}
