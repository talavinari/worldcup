package worldcup.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
