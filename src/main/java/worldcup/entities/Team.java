package worldcup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
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
