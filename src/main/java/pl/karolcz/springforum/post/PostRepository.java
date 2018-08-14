package pl.karolcz.springforum.post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.karolcz.springforum.user.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public interface PostRepository extends CrudRepository<Post, Long> {
    @Transactional
//    @Query(value = "Select p.body from Post p join fetch p.user u where u.id = ?1")
    List<Post> findAllByUser(User user);
}
