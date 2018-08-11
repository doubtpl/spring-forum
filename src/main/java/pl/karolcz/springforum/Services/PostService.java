package pl.karolcz.springforum.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.karolcz.springforum.Models.Post;
import pl.karolcz.springforum.Models.User;
import pl.karolcz.springforum.Repositories.PostRepository;
import pl.karolcz.springforum.Repositories.UserRepository;

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
        User user = userRepository.findByUsername(username);
        Post post = new Post();
        post.setUser(user);
        post.setBody(body);
        postRepository.save(post);

        return ResponseEntity.ok().build();
    }
}
