package worldcup.api.dtos;

import java.util.Objects;

public class SoccerPlayerStatDto {
    private String name;
    private Integer numberOfGoals;

    public SoccerPlayerStatDto() {
    }

    public SoccerPlayerStatDto(String name, Integer numberOfGoals) {
        this.name = name;
        this.numberOfGoals = numberOfGoals;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoccerPlayerStatDto that = (SoccerPlayerStatDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(numberOfGoals, that.numberOfGoals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfGoals);
    }

    @Override
    public String toString() {
        return "SoccerPlayerStatDto{" +
                "name='" + name + '\'' +
                ", numberOfGoals=" + numberOfGoals +
                '}';
    }
}
