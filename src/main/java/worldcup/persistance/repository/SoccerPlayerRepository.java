package worldcup.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.persistance.entities.SoccerPlayer;

public interface SoccerPlayerRepository extends CrudRepository<SoccerPlayer, Long> {
}
