package worldcup.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.entities.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
