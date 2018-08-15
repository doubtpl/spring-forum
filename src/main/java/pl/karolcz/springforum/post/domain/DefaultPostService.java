package pl.karolcz.springforum.post.domain;

import org.springframework.transaction.annotation.Transactional;

import pl.karolcz.springforum.post.CreatePostCommand;
import pl.karolcz.springforum.post.Post;
import pl.karolcz.springforum.post.PostNotFoundException;
import pl.karolcz.springforum.post.PostService;
import pl.karolcz.springforum.post.UpdatePostCommand;
import pl.karolcz.springforum.user.User;
import pl.karolcz.springforum.user.UserNotFoundException;
import pl.karolcz.springforum.user.UserService;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DefaultPostService implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    private final PostDomainTranslator domainTranslator;

    @Override
    @Transactional
    public Post createPost(CreatePostCommand createPostCommand) throws UserNotFoundException {
        User user = userService.getUser(createPostCommand.getAuthorUserId());
        Post newPost = domainTranslator.toNewPost(createPostCommand, user);
        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public Post updatePost(Long postId, UpdatePostCommand updatePostCommand) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.setBody(updatePostCommand.getContent());
        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Post getPost(Long postId) throws PostNotFoundException {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getPostsByUser(Long userId) throws UserNotFoundException {
        User authorUser = userService.getUser(userId);
        return postRepository.findAllByUser(authorUser);
    }

    @Override
    @Transactional
    public void deletePost(Long postId) throws PostNotFoundException {
        postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        postRepository.deleteById(postId);
    }
}
