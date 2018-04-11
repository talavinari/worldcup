package worldcup.entities;

import javax.persistence.*;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "rank_A")
    private String rankA;
    @Column(name = "rank_B")
    private String rankB;
    @Column(name = "rank_C")
    private String rankC;
    @Column(name = "rank_D")
    private String rankD;
    @Column(name = "best_scorer")
    private String bestScorer;
    @Column(name = "best_attack")
    private String bestAttack;
    @Column(name = "worst_defence")
    private String worstDefence;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Bet() {
    }

    public Bet(String rankA, String rankB, String rankC, String rankD, String bestScorer, String bestAttack, String worstDefence, User user) {
        this.rankA = rankA;
        this.rankB = rankB;
        this.rankC = rankC;
        this.rankD = rankD;
        this.bestScorer = bestScorer;
        this.bestAttack = bestAttack;
        this.worstDefence = worstDefence;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Bet{" +
                "id=" + id +
                ", rankA='" + rankA + '\'' +
                ", rankB='" + rankB + '\'' +
                ", rankC='" + rankC + '\'' +
                ", rankD='" + rankD + '\'' +
                ", bestScorer='" + bestScorer + '\'' +
                ", bestAttack='" + bestAttack + '\'' +
                ", worstDefence='" + worstDefence + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
