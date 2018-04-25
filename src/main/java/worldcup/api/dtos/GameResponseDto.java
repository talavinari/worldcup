package worldcup.api.dtos;

import java.util.Objects;

public class GameResponseDto {

    private Long id;
    private String team1;
    private String team2;
    private Integer score1;
    private Integer score2;
    private Integer extraTimeScore1;
    private Integer extraTimeScore2;
    private Integer penaltyScore1;
    private Integer penaltyScore2;
    private String date;
    private String gameTime;
    private String stage;
    private Boolean shouldOverride;

    public GameResponseDto() {
    }

    public GameResponseDto(Long id, String team1, String team2, Integer score1, Integer score2,
                           Integer extraTimeScore1, Integer extraTimeScore2, Integer penaltyScore1,
                           Integer penaltyScore2, String gameTime, String stage, Boolean shouldOverride) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.extraTimeScore1 = extraTimeScore1;
        this.extraTimeScore2 = extraTimeScore2;
        this.penaltyScore1 = penaltyScore1;
        this.penaltyScore2 = penaltyScore2;
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

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
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
        GameResponseDto gameResponseDto = (GameResponseDto) o;
        return Objects.equals(id, gameResponseDto.id) &&
                Objects.equals(team1, gameResponseDto.team1) &&
                Objects.equals(team2, gameResponseDto.team2) &&
                Objects.equals(score1, gameResponseDto.score1) &&
                Objects.equals(score2, gameResponseDto.score2) &&
                Objects.equals(gameTime, gameResponseDto.gameTime) &&
                Objects.equals(stage, gameResponseDto.stage) &&
                Objects.equals(shouldOverride, gameResponseDto.shouldOverride);
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
