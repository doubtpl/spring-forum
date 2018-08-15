package pl.karolcz.springforum.post.web;

import pl.karolcz.springforum.post.CreatePostCommand;
import pl.karolcz.springforum.post.Post;
import pl.karolcz.springforum.post.UpdatePostCommand;

class PostWebTranslator {

    PostDto toDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .authorUserId(post.getUser().getId())
                .content(post.getBody())
                .build();
    }

    CreatePostCommand toCreatePostCommand(Long userId, CreateUpdatePostDto createUpdatePostDto) {
        return CreatePostCommand.builder()
                .authorUserId(userId)
                .content(createUpdatePostDto.getContent())
                .build();
    }

    UpdatePostCommand toUpdatePostCommand(CreateUpdatePostDto createUpdatePostDto) {
        return UpdatePostCommand.builder()
                .content(createUpdatePostDto.getContent())
                .build();
    }
}
