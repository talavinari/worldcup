package worldcup.api.dtos;

public class GameMetadataDto {
    private String team1;
    private String team2;

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

    @Override
    public String toString() {
        return "GameMetadataDto{" +
                "team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                '}';
    }
}
