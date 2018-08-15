package pl.karolcz.springforum;

import org.springframework.stereotype.Component;

import pl.karolcz.springforum.post.CreatePostCommand;
import pl.karolcz.springforum.post.PostService;
import pl.karolcz.springforum.user.CreateUpdateUserCommand;
import pl.karolcz.springforum.user.User;
import pl.karolcz.springforum.user.UserNotFoundException;
import pl.karolcz.springforum.user.UserService;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ExampleDataInitializer {

    private final UserService userService;
    private final PostService postService;

    @PostConstruct
    public void saveExampleData() throws UserNotFoundException {
        User andrzejUser = userService.createUser(CreateUpdateUserCommand.builder()
                .username("andrzej")
                .password("changeit")
                .build());

        postService.createPost(CreatePostCommand.builder()
                .authorUserId(andrzejUser.getId())
                .content("Jestem andrzej, a to moj pierwszy post")
                .build());
    }

}
