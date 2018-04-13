package worldcup.api.dtos;

public class TeamGamesBalance {
    private String teamName;
    private int totalWins;
    private int winsAgaintBetterRankTeam;
    private int loses;
    private int losesAgainstLowerRankTeam;
    private int ties;

    public TeamGamesBalance() {
    }

    public TeamGamesBalance(String teamName) {
        this.teamName = teamName;
        this.totalWins = 0;
        this.winsAgaintBetterRankTeam = 0;
        this.loses = 0;
        this.losesAgainstLowerRankTeam = 0;
        this.ties = 0;
    }

    public TeamGamesBalance(String teamName, int totalWins, int winsAgaintBetterRankTeam, int loses, int losesAgainstLowerRankTeam, int ties) {
        this.teamName = teamName;
        this.totalWins = totalWins;
        this.winsAgaintBetterRankTeam = winsAgaintBetterRankTeam;
        this.loses = loses;
        this.losesAgainstLowerRankTeam = losesAgainstLowerRankTeam;
        this.ties = ties;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getTies() {
        return ties;
    }


    public void setTies(int ties) {
        this.ties = ties;
    }

    public void incrementTie() {
        this.ties++;
    }

    public void incrementTotalWin() {
        this.totalWins++;
    }


    public void incrementTotalLoses() {
        this.loses++;
    }

    public void incrementWinsAgainstBetterRankTeam() {
        this.winsAgaintBetterRankTeam++;
    }
    public void incrementLosesAgainstLowerRankTeam() {
        this.losesAgainstLowerRankTeam++;
    }


    public int getWinsAgaintBetterRankTeam() {
        return winsAgaintBetterRankTeam;
    }

    public void setWinsAgaintBetterRankTeam(int winsAgaintBetterRankTeam) {
        this.winsAgaintBetterRankTeam = winsAgaintBetterRankTeam;
    }

    public int getLosesAgainstLowerRankTeam() {
        return losesAgainstLowerRankTeam;
    }

    public void setLosesAgainstLowerRankTeam(int losesAgainstLowerRankTeam) {
        this.losesAgainstLowerRankTeam = losesAgainstLowerRankTeam;
    }

    @Override
    public String toString() {
        return "TeamGamesBalance{" +
                "teamName='" + teamName + '\'' +
                ", totalWins=" + totalWins +
                ", winsAgaintBetterRankTeam=" + winsAgaintBetterRankTeam +
                ", loses=" + loses +
                ", losesAgainstLowerRankTeam=" + losesAgainstLowerRankTeam +
                ", ties=" + ties +
                '}';
    }
}
