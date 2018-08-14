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
import pl.karolcz.springforum.user.User;
import pl.karolcz.springforum.user.UserRepository;
import pl.karolcz.springforum.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;

    private User user;
    private List<Post> posts = new ArrayList<>();

    @BeforeEach
    void init() {
        this.user = userRepository.findByUsername("James").get();
        posts.clear();
        postRepository.findAll().forEach(posts::add);
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
    void getExistingUser() {
        ResponseEntity response = userService.getUser(this.user.getUsername());
        Assertions.assertEquals(user.toString(), Objects.requireNonNull(response.getBody()).toString());
    }

    @Test
    void getNotExistingUser() {
        ResponseEntity response = userService.getUser("quiteLongStringSoNooneWillAddUserWithThatUsername" +
                "ToTheDemoDataClass");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
