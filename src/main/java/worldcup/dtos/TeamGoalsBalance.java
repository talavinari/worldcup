package worldcup.dtos;

public class TeamGoalsBalance {
    private String teamName;
    private Integer goalsFor;
    private Integer goalsAgainst;

    public TeamGoalsBalance() {
    }

    public TeamGoalsBalance(String teamName, Integer goalsFor, Integer goalsAgainst) {
        this.teamName = teamName;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Override
    public String toString() {
        return "TeamGoalsBalance{" +
                "teamName='" + teamName + '\'' +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                '}';
    }
}
