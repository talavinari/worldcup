package worldcup.api.dtos;

import java.util.Date;
import java.util.Objects;

public class NewGameDto {

    private String team1;
    private String team2;
    private Date gameTime;
    private String stage;
    private Boolean shouldOverride;

    public NewGameDto() {
    }

    public NewGameDto(String team1, String team2,
                      Date gameTime, String stage, Boolean shouldOverride) {
        this.team1 = team1;
        this.team2 = team2;
        this.gameTime = gameTime;
        this.stage = stage;
        this.shouldOverride = shouldOverride;
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
    public String toString() {
        return "NewGameDto{" +
                "team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", gameTime=" + gameTime +
                ", stage='" + stage + '\'' +
                ", shouldOverride=" + shouldOverride +
                '}';
    }
}
