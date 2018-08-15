package pl.karolcz.springforum.post.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class PostDto {

    private Long id;

    private Long authorUserId;

    private String content;
}
