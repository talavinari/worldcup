package worldcup.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.persistance.entities.Game;

public interface GameRepository extends CrudRepository<Game, Long> {
}
