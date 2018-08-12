package pl.karolcz.springforum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.karolcz.springforum.Models.Post;
import pl.karolcz.springforum.Models.User;
import pl.karolcz.springforum.Repositories.PostRepository;
import pl.karolcz.springforum.Repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DemoData {

    private final
    UserRepository userRepository;
    private final
    PostRepository postRepository;

    @Autowired
    public DemoData(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        loadData();
    }

    private void loadData() {
        User user = User.builder().username("James").password("somepassword").build();
        List<Post> posts = Arrays.asList(
                Post.builder().body("Hello everybody! I am James").user(user).build(),
                Post.builder().body("This is post body").user(user).build()
        );
        userRepository.save(user);
        postRepository.saveAll(posts);
    }
}
