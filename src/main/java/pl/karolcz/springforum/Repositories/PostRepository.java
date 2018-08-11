package pl.karolcz.springforum.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.karolcz.springforum.Models.Post;
import pl.karolcz.springforum.Models.User;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByUser(User user);
}
