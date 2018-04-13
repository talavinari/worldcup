package worldcup.persistance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "team1")
    private String team1;
    @Column(name = "team2")
    private String team2;
    @Column(name = "score1")
    private Integer score1;
    @Column(name = "score2")
    private Integer score2;
    @Column(name = "game_time")
    private Date gameTime;
    @Column(name = "stage")
    private String stage;
    @Column(name = "should_override")
    private Boolean shouldOverride;

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
