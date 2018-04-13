package worldcup.Services.interfaces;

import worldcup.api.dtos.GameResultDto;
import worldcup.api.dtos.GamesResponseDto;
import worldcup.persistance.entities.Game;

import java.util.List;

public interface GameService {
    boolean isKnockOutStage();

    boolean isTournamentFinished();

    List<Game> getAllGames();

    List<Game> getFinishedGames();

    Game findById(Long gameId);

    Game save(Game game);

    Game updateGameResult(GameResultDto gameResult);

    GamesResponseDto getAllGamesByGameTime();
}
