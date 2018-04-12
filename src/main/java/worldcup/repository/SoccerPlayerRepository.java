package worldcup.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.entities.SoccerPlayer;

public interface SoccerPlayerRepository extends CrudRepository<SoccerPlayer, Long> {
}
