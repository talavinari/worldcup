package worldcup.dtos;

import worldcup.entities.Game;

import java.util.LinkedList;
import java.util.List;

public class GamesResponseDto {
    List<Game> today;
    List<Game> future;
    List<Game> finished;

    public GamesResponseDto() {
        today = new LinkedList<>();
        future = new LinkedList<>();
        finished = new LinkedList<>();
    }

    public GamesResponseDto(List<Game> today, List<Game> future, List<Game> finished) {
        this.today = today;
        this.future = future;
        this.finished = finished;
    }

    public List<Game> getToday() {
        return today;
    }

    public void setToday(List<Game> today) {
        this.today = today;
    }

    public List<Game> getFuture() {
        return future;
    }

    public void setFuture(List<Game> future) {
        this.future = future;
    }

    public List<Game> getFinished() {
        return finished;
    }

    public void setFinished(List<Game> finished) {
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
