package worldcup.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.entities.Bet;

public interface BetRepository extends CrudRepository<Bet, Long> {
}
