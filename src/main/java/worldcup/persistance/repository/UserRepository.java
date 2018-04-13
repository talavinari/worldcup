package worldcup.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.persistance.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
