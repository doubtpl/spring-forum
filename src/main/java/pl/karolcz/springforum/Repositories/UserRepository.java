package pl.karolcz.springforum.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.karolcz.springforum.Models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}