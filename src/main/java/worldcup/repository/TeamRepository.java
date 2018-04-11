package worldcup.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.entities.Bet;
import worldcup.entities.Team;

public interface TeamRepository extends CrudRepository<Team, String> {
}
