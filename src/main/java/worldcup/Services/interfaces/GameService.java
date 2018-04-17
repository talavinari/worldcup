package worldcup.Services.interfaces;

import worldcup.Services.enums.GameStage;
import worldcup.api.dtos.GameMetadataDto;
import worldcup.api.dtos.GameResultDto;
import worldcup.api.dtos.GamesResponseDto;
import worldcup.api.dtos.NewGameDto;
import worldcup.persistance.entities.Game;

import java.util.List;
import java.util.Map;

public interface GameService {
    boolean isKnockOutStage();

    boolean isTournamentFinished();

    List<Game> getAllGames();

    List<Game> getFinishedGames();

    Game findById(Long gameId);

    Game save(Game game);

    Game updateGameResult(Long gameId, GameResultDto gameResult);

    GamesResponseDto getAllGamesByGameTime(String gameStage);

    List<Game> getGamesByStage(GameStage gameStage);

    Map<GameStage, List<String>> getTeamsByStages();

    Game updateGameMetadata(GameMetadataDto gameMetadataDto, Long gameId);

    Game createGame(NewGameDto newGameDto);
}
