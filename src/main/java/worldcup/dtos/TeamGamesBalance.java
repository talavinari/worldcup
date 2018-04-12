package worldcup.dtos;

public class TeamGamesBalance {
    private int totalWins;
    private int winsAgaintBetterRankTeam;
    private int loses;
    private int losesAgainstLowerRankTeam;
    private int ties;

    public TeamGamesBalance() {
        this.totalWins = 0;
        this.winsAgaintBetterRankTeam = 0;
        this.loses = 0;
        this.losesAgainstLowerRankTeam = 0;
        this.ties = 0;
    }

    public TeamGamesBalance(int totalWins, int winsAgaintBetterRankTeam, int loses, int losesAgainstLowerRankTeam, int ties) {
        this.totalWins = totalWins;
        this.winsAgaintBetterRankTeam = winsAgaintBetterRankTeam;
        this.loses = loses;
        this.losesAgainstLowerRankTeam = losesAgainstLowerRankTeam;
        this.ties = ties;
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
                "totalWins=" + totalWins +
                ", winsAgaintBetterRankTeam=" + winsAgaintBetterRankTeam +
                ", loses=" + loses +
                ", losesAgainstLowerRankTeam=" + losesAgainstLowerRankTeam +
                ", ties=" + ties +
                '}';
    }
}
