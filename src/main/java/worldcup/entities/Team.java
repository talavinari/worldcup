package worldcup.entities;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "rank")
    private String rank;
    @Column(name = "final_pos_in_group_stage")
    private Long finalPosInGroupStage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Long getFinalPosInGroupStage() {
        return finalPosInGroupStage;
    }

    public void setFinalPosInGroupStage(Long finalPosInGroupStage) {
        this.finalPosInGroupStage = finalPosInGroupStage;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", finalPosInGroupStage=" + finalPosInGroupStage +
                '}';
    }
}
