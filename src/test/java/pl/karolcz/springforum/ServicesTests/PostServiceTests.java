package pl.karolcz.springforum.ServicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.karolcz.springforum.post.Post;
import pl.karolcz.springforum.post.PostRepository;
import pl.karolcz.springforum.post.PostService;
import pl.karolcz.springforum.user.User;
import pl.karolcz.springforum.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostServiceTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;

    private User user;
    private List<Post> posts = new ArrayList<>();

    @BeforeEach
    void init() {
        this.user = userRepository.findByUsername("James").get();
        posts.clear();
        postRepository.findAll().forEach(posts::add);
    }

    @Test
    void addValidPost() {
        ResponseEntity response = postService.addPost("Test body", "James");
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addNotValidPost() {
        ResponseEntity response = postService.addPost("Body of post that has bad user assigned", "Max");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getExistingUserPosts() {
        ResponseEntity response = postService.findAllByUser(this.user.getUsername());
        Assertions.assertEquals(posts.toString(), Objects.requireNonNull(response.getBody()).toString());
    }

    @Test
    void getNotExistingUserPosts() {
        ResponseEntity response = postService.findAllByUser("Elton");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
