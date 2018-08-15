package pl.karolcz.springforum.post.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.karolcz.springforum.post.PostService;
import pl.karolcz.springforum.user.UserService;

@Configuration
class PostConfiguration {

    @Bean
    PostService postService(PostRepository postRepository, UserService userService) {
        return new DefaultPostService(postRepository, userService, new PostDomainTranslator());
    }
}
