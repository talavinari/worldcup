package worldcup.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.persistance.entities.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
