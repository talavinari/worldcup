package worldcup.api.dtos;

public class BetDtoResponse {

    private String rankA;
    private String rankB;
    private String rankC;
    private String rankD;
    private Integer rankAPoints;
    private Integer rankBPoints;
    private Integer rankCPoints;
    private Integer rankDPoints;
    private String bestScorer;
    private String bestAttack;
    private String worstDefence;
    private String name;
    private String email;
    private Integer points;
    private String group;

    public BetDtoResponse(String rankA,
                          int rankAPoints,
                          String rankB,
                          int rankBPoints,
                          String rankC,
                          int rankCPoints,
                          String rankD,
                          int rankDPoints,
                          String bestScorer,
                          String bestAttack,
                          String worstDefence,
                          String name,
                          String email,
                          Integer points,
                          String group) {
        this.rankA = rankA;
        this.rankAPoints = rankAPoints;
        this.rankB = rankB;
        this.rankBPoints = rankBPoints;
        this.rankC = rankC;
        this.rankCPoints = rankCPoints;
        this.rankD = rankD;
        this.rankDPoints = rankDPoints;
        this.bestScorer = bestScorer;
        this.bestAttack = bestAttack;
        this.worstDefence = worstDefence;
        this.name = name;
        this.email = email;
        this.points = points;
        this.group = group;

    }

    public String getRankA() {
        return rankA;
    }

    public void setRankA(String rankA) {
        this.rankA = rankA;
    }

    public String getRankB() {
        return rankB;
    }

    public void setRankB(String rankB) {
        this.rankB = rankB;
    }

    public String getRankC() {
        return rankC;
    }

    public void setRankC(String rankC) {
        this.rankC = rankC;
    }

    public String getRankD() {
        return rankD;
    }

    public void setRankD(String rankD) {
        this.rankD = rankD;
    }

    public String getBestScorer() {
        return bestScorer;
    }

    public void setBestScorer(String bestScorer) {
        this.bestScorer = bestScorer;
    }

    public String getBestAttack() {
        return bestAttack;
    }

    public void setBestAttack(String bestAttack) {
        this.bestAttack = bestAttack;
    }

    public String getWorstDefence() {
        return worstDefence;
    }

    public void setWorstDefence(String worstDefence) {
        this.worstDefence = worstDefence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getRankAPoints() {
        return rankAPoints;
    }

    public void setRankAPoints(Integer rankAPoints) {
        this.rankAPoints = rankAPoints;
    }

    public Integer getRankBPoints() {
        return rankBPoints;
    }

    public void setRankBPoints(Integer rankBPoints) {
        this.rankBPoints = rankBPoints;
    }

    public Integer getRankCPoints() {
        return rankCPoints;
    }

    public void setRankCPoints(Integer rankCPoints) {
        this.rankCPoints = rankCPoints;
    }

    public Integer getRankDPoints() {
        return rankDPoints;
    }

    public void setRankDPoints(Integer rankDPoints) {
        this.rankDPoints = rankDPoints;
    }
}
