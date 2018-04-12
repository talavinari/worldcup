package worldcup.Services;

import worldcup.entities.Game;

import java.util.List;

public interface GameService {
    boolean isKnockOutStage();

    boolean isTournamentFinished();

    List<Game> getAllGames();

    List<Game> getFinishedGames();
}
