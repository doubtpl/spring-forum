package pl.karolcz.springforum.user.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.karolcz.springforum.user.User;

import java.util.Optional;

@Repository
interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}