package worldcup.persistance.entities;

import worldcup.Services.enums.GameStage;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private GameStage stage;
    @Column(name = "should_override")
    private Boolean shouldOverride;
    @Column(name = "extra_time_score1")
    private Integer extraTimeScore1;
    @Column(name = "extra_time_score2")
    private Integer extraTimeScore2;
    @Column(name = "penalty_score1")
    private Integer penaltyScore1;
    @Column(name = "penalty_score2")
    private Integer penaltyScore2;


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

    public GameStage getStage() {
        return stage;
    }

    public void setStage(GameStage stage) {
        this.stage = stage;
    }

    public Boolean getShouldOverride() {
        return shouldOverride;
    }

    public void setShouldOverride(Boolean shouldOverride) {
        this.shouldOverride = shouldOverride;
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", gameTime=" + gameTime +
                ", stage=" + stage +
                ", shouldOverride=" + shouldOverride +
                ", extraTimeScore1=" + extraTimeScore1 +
                ", extraTimeScore2=" + extraTimeScore2 +
                ", penaltyScore1=" + penaltyScore1 +
                ", penaltyScore2=" + penaltyScore2 +
                '}';
    }
}
