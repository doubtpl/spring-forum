package pl.karolcz.springforum.post;

import pl.karolcz.springforum.user.UserNotFoundException;

import java.util.List;

public interface PostService {

    Post createPost(CreatePostCommand createPostCommand) throws UserNotFoundException;

    Post updatePost(Long postId, UpdatePostCommand updatePostCommand) throws PostNotFoundException;

    Post getPost(Long postId) throws PostNotFoundException;

    List<Post> getPostsByUser(Long userId) throws UserNotFoundException;

    void deletePost(Long postId) throws PostNotFoundException;
}
