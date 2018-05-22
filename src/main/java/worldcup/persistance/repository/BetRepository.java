package worldcup.persistance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import worldcup.persistance.entities.Bet;
import worldcup.persistance.entities.User;

import java.util.List;

public interface BetRepository extends CrudRepository<Bet, Long> {

    @Query(value = "select g from Bet g " +
            "where g.user = ?1")
    List<Bet> findBetsByUser(User user);

}
