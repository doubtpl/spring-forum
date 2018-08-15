package pl.karolcz.springforum.post.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.karolcz.springforum.post.Post;
import pl.karolcz.springforum.user.User;

import java.util.List;

@Repository
interface PostRepository extends CrudRepository<Post, Long> {
    @Query(value = "Select p from Post p JOIN FETCH p.user u where u=?1")
    List<Post> findAllByUser(User user);
}
