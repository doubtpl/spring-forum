package pl.karolcz.springforum.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.karolcz.springforum.Models.Post;
import pl.karolcz.springforum.Models.User;
import pl.karolcz.springforum.Repositories.UserRepository;

import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity getAllUserPosts(String username) {
        User user = userRepository.findByUsername(username);
        List<Post> posts = user.getPosts();
        return ResponseEntity.accepted().body(posts);
    }

    public ResponseEntity getUser(String username) {
        User user = userRepository.findByUsername(username);
        return ResponseEntity.ok().body(user);
    }
}