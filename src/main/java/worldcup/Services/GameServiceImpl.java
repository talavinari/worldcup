package worldcup.Services;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import worldcup.entities.Game;
import worldcup.repository.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    public static final int GROUP_STAGE_GAME_COUNT = 48;
    public static final int TOTAL_GAME_COUNT = 64;
    private GameRepository gameRepository;

    @Override
    public boolean isKnockOutStage() {
        return getFinishedGames().size() >= GROUP_STAGE_GAME_COUNT;
    }

    @Override
    public boolean isTournamentFinished() {
        return getFinishedGames().size() >= TOTAL_GAME_COUNT;
    }

    @Override
    public List<Game> getAllGames() {
        return Lists.newArrayList(gameRepository.findAll());
    }

    @Override
    public List<Game> getFinishedGames() {
        return getAllGames().stream().
                filter(this::isGameFinished).
                collect(Collectors.toList());
    }

    private boolean isGameFinished(Game game) {
        return game.getScore1() != null && game.getScore2() != null;
    }
}
