package pl.karolcz.springforum.post.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.karolcz.springforum.post.PostNotFoundException;
import pl.karolcz.springforum.post.PostService;
import pl.karolcz.springforum.post.UpdatePostCommand;
import pl.karolcz.springforum.user.User;
import pl.karolcz.springforum.user.UserNotFoundException;
import pl.karolcz.springforum.user.UserService;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
class PostController {
    private final PostService postService;
    private final UserService userService;

    private final PostWebTranslator webTranslator;

    @GetMapping("/{postId}")
    public PostDto getPost(@PathVariable("postId") Long postId) throws PostNotFoundException {
        return webTranslator.toDto(postService.getPost(postId));
    }

    @GetMapping
    public PostsDto getPostForUsername(@RequestParam("username") String username) throws UserNotFoundException {
        User user = userService.getUserByUsername(username);
        return new PostsDto(
                postService.getPostsByUser(user.getId()).stream()
                        .map(webTranslator::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PutMapping(value = "/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostDto updatePost(@PathVariable("postId") Long postId, @Validated @RequestBody CreateUpdatePostDto updatePostDto) throws PostNotFoundException {
        UpdatePostCommand updateCommand = webTranslator.toUpdatePostCommand(updatePostDto);
        return webTranslator.toDto(postService.updatePost(postId, updateCommand));
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("postId") Long postId) throws PostNotFoundException {
        postService.deletePost(postId);
    }

}
