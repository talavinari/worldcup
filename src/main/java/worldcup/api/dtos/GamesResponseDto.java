package worldcup.api.dtos;

import java.util.LinkedList;
import java.util.List;

public class GamesResponseDto {
    List<GameDto> today;
    List<GameDto> future;
    List<GameDto> finished;

    public GamesResponseDto() {
        today = new LinkedList<>();
        future = new LinkedList<>();
        finished = new LinkedList<>();
    }

    public GamesResponseDto(List<GameDto> today, List<GameDto> future, List<GameDto> finished) {
        this.today = today;
        this.future = future;
        this.finished = finished;
    }

    public List<GameDto> getToday() {
        return today;
    }

    public void setToday(List<GameDto> today) {
        this.today = today;
    }

    public List<GameDto> getFuture() {
        return future;
    }

    public void setFuture(List<GameDto> future) {
        this.future = future;
    }

    public List<GameDto> getFinished() {
        return finished;
    }

    public void setFinished(List<GameDto> finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "GamesResponseDto{" +
                "today=" + today +
                ", future=" + future +
                ", finished=" + finished +
                '}';
    }
}
