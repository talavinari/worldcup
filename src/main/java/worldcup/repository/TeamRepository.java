package worldcup.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.entities.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, String> {
    List<Team> findByRank(String rank);
}
