package worldcup.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "soccer_player")
public class SoccerPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "number_of_goals")
    private Integer numberOfGoals;

    public SoccerPlayer() {
    }

    public SoccerPlayer(String name, Integer numberOfGoals) {
        this.name = name;
        this.numberOfGoals = numberOfGoals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfGoals() {
        return numberOfGoals;
    }

    public void setNumberOfGoals(Integer numberOfGoals) {
        this.numberOfGoals = numberOfGoals;
    }

    public void addGoals(Integer addedGoals) {
        this.numberOfGoals = this.numberOfGoals + addedGoals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoccerPlayer that = (SoccerPlayer) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(numberOfGoals, that.numberOfGoals);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, numberOfGoals);
    }

    @Override
    public String toString() {
        return "SoccerPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfGoals=" + numberOfGoals +
                '}';
    }
}
