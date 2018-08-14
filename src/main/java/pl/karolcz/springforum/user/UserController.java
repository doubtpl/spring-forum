package pl.karolcz.springforum.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody Map<String, String> credentials) {
        return userService.addUser(credentials.get("username"), credentials.get("password"));
    }

    @GetMapping("/{username}/posts")
    public ResponseEntity getAllUserPosts(@PathVariable("username") String username) {
        return userService.getAllUserPosts(username);
    }

    @GetMapping("/{username}")
    public ResponseEntity getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

}
