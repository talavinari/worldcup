package worldcup.Services;

import worldcup.entities.Game;

import java.util.List;

public interface GameService {
    boolean isKnockOutStage();

    List<Game> getAllGames();

    List<Game> getFinishedGames();
}
