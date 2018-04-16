package worldcup.api.dtos;

import java.util.LinkedList;
import java.util.List;

public class GamesResponseDto {
    List<GameResponseDto> today;
    List<GameResponseDto> future;
    List<GameResponseDto> finished;

    public GamesResponseDto() {
        today = new LinkedList<>();
        future = new LinkedList<>();
        finished = new LinkedList<>();
    }

    public GamesResponseDto(List<GameResponseDto> today, List<GameResponseDto> future, List<GameResponseDto> finished) {
        this.today = today;
        this.future = future;
        this.finished = finished;
    }

    public List<GameResponseDto> getToday() {
        return today;
    }

    public void setToday(List<GameResponseDto> today) {
        this.today = today;
    }

    public List<GameResponseDto> getFuture() {
        return future;
    }

    public void setFuture(List<GameResponseDto> future) {
        this.future = future;
    }

    public List<GameResponseDto> getFinished() {
        return finished;
    }

    public void setFinished(List<GameResponseDto> finished) {
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
