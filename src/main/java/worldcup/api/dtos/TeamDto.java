package worldcup.api.dtos;

import java.util.Objects;

public class TeamDto {
    private String name;
    private String rank;
    private Long finalPosInGroupStage;

    public TeamDto() {
    }

    public TeamDto(String name, String rank, Long finalPosInGroupStage) {
        this.name = name;
        this.rank = rank;
        this.finalPosInGroupStage = finalPosInGroupStage;
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
        TeamDto teamDto = (TeamDto) o;
        return Objects.equals(name, teamDto.name) &&
                Objects.equals(rank, teamDto.rank) &&
                Objects.equals(finalPosInGroupStage, teamDto.finalPosInGroupStage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, rank, finalPosInGroupStage);
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", finalPosInGroupStage=" + finalPosInGroupStage +
                '}';
    }
}
