package pl.karolcz.springforum.post.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.karolcz.springforum.post.PostService;
import pl.karolcz.springforum.user.UserService;

@Configuration
class PostWebConfiguration {

    @Bean
    PostController postController(PostService postService, UserService userService) {
        return new PostController(postService, userService, postWebTranslator());
    }

    @Bean
    CreatePostController createPostController(PostService postService) {
        return new CreatePostController(postService, postWebTranslator());
    }

    private PostWebTranslator postWebTranslator() {
        return new PostWebTranslator();
    }
}
