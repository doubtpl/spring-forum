package pl.karolcz.springforum.post.domain;

import pl.karolcz.springforum.post.CreatePostCommand;
import pl.karolcz.springforum.post.Post;
import pl.karolcz.springforum.user.User;

class PostDomainTranslator {

    Post toNewPost(CreatePostCommand createPostCommand, User user) {
        return Post.builder()
                .body(createPostCommand.getContent())
                .user(user)
                .build();
    }
}
