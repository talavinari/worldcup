package worldcup.dtos;

public class ScorerDto {
    private String name;
    private Integer numberOfGoals;

    public ScorerDto() {
    }

    public ScorerDto(String name, Integer numberOfGoals) {
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
    public String toString() {
        return "ScorerDto{" +
                "name='" + name + '\'' +
                ", numberOfGoals=" + numberOfGoals +
                '}';
    }
}
