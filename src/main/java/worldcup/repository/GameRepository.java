package worldcup.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.entities.Game;

public interface GameRepository extends CrudRepository<Game, Long> {
}
