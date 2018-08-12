package pl.karolcz.springforum.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.karolcz.springforum.Models.User;
import pl.karolcz.springforum.Repositories.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity addUser(String username, String password) {
        User newUser = User.builder().username(username).password(password).build();
        Optional<User> user = userRepository.findByUsername(username);

        return user
                .map(theUser -> new ResponseEntity<>(HttpStatus.CONFLICT))
                .orElseGet(() -> {
                    log.debug("wtf");
                    userRepository.save(newUser);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                });
    }

    public ResponseEntity getAllUserPosts(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user
                .map(theUser -> new ResponseEntity<>(theUser.getPosts(), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user
                .map(theUser -> new ResponseEntity<>(theUser, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}