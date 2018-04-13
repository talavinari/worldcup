package worldcup.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.persistance.entities.Bet;

public interface BetRepository extends CrudRepository<Bet, Long> {
}
