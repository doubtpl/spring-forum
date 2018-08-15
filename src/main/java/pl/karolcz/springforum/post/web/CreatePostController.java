package pl.karolcz.springforum.post.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.karolcz.springforum.post.CreatePostCommand;
import pl.karolcz.springforum.post.PostService;
import pl.karolcz.springforum.user.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users/{userId}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
class CreatePostController {
    private final PostService postService;

    private final PostWebTranslator webTranslator;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addPost(@PathVariable("userId") Long userId, @Validated @RequestBody CreateUpdatePostDto createUpdatePostDto) throws UserNotFoundException {
        CreatePostCommand createCommand = webTranslator.toCreatePostCommand(userId, createUpdatePostDto);
        return webTranslator.toDto(postService.createPost(createCommand));
    }

}
