package worldcup.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import worldcup.persistance.entities.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);

}
