package pl.karolcz.springforum.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.karolcz.springforum.Models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}