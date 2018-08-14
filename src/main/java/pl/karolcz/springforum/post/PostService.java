package pl.karolcz.springforum.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.karolcz.springforum.user.User;
import pl.karolcz.springforum.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity addPost(String body, String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user
                .map(theUser -> {
                    Post post = Post.builder().body(body).user(theUser).build();
                    postRepository.save(post);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    public ResponseEntity findAllByUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user
                .map(theUser -> {
                    List posts = postRepository.findAllByUser(theUser);
                    return new ResponseEntity<>(posts, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
