package pl.karolcz.springforum.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody Map<String, String> data) {
        return postService.addPost(data.get("body"), data.get("username"));
    }

    @GetMapping("/{username}")
    public ResponseEntity getAllUserPosts(@PathVariable("username") String username) {
        return postService.findAllByUser(username);
    }
}
