package worldcup.persistance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import worldcup.api.enums.GameStage;
import worldcup.persistance.entities.Game;

import java.util.Set;

public interface GameRepository extends CrudRepository<Game, Long> {

    @Query(value = "select g from Game g " +
            "where g.stage = ?1")
    Set<Game> findGamesByLevel(GameStage stage);
}
