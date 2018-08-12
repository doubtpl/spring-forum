package pl.karolcz.springforum.ServicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.karolcz.springforum.Models.Post;
import pl.karolcz.springforum.Models.User;
import pl.karolcz.springforum.Repositories.PostRepository;
import pl.karolcz.springforum.Repositories.UserRepository;
import pl.karolcz.springforum.Services.UserService;

import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;

    private User user;
    private List<Post> posts;

    @BeforeAll
    void init() {
        this.user = userRepository.findByUsername("James").get();
        this.posts = postRepository.findAllByUser(this.user);
    }

    @Test
    void addExistingUser() {
        ResponseEntity response = userService.addUser(this.user.getUsername(), this.user.getPassword());
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void addNotExistingUser() {
        ResponseEntity response = userService.addUser("John", "aaa");
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getExistingUserPosts() {
        ResponseEntity response = userService.getAllUserPosts(this.user.getUsername());
        Assertions.assertEquals(posts.toString(), Objects.requireNonNull(response.getBody()).toString());
    }

    @Test
    void getNotExistingUserPosts() {
        ResponseEntity response = userService.getAllUserPosts("Elton");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getExistingUser() {
        ResponseEntity response = userService.getUser(this.user.getUsername());
        Assertions.assertEquals(user.toString(), Objects.requireNonNull(response.getBody()).toString());
    }

    @Test
    void getNotExistingUser() {
        ResponseEntity response = userService.getUser("Elton");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
