package worldcup.api.dtos;

public class BetDto {

    private String rankA;
    private String rankB;
    private String rankC;
    private String rankD;
    private String bestScorer;
    private String bestAttack;
    private String worstDefence;
    public BetDto() {
    }

    public BetDto(String rankA, String rankB, String rankC, String rankD, String bestScorer, String bestAttack, String worstDefence) {
        this.rankA = rankA;
        this.rankB = rankB;
        this.rankC = rankC;
        this.rankD = rankD;
        this.bestScorer = bestScorer;
        this.bestAttack = bestAttack;
        this.worstDefence = worstDefence;
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

    @Override
    public String toString() {
        return "BetDto{" +
                "rankA='" + rankA + '\'' +
                ", rankB='" + rankB + '\'' +
                ", rankC='" + rankC + '\'' +
                ", rankD='" + rankD + '\'' +
                ", bestScorer='" + bestScorer + '\'' +
                ", bestAttack='" + bestAttack + '\'' +
                ", worstDefence='" + worstDefence + '\'' +
                '}';
    }
}
