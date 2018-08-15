package pl.karolcz.springforum.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostNotFoundException extends Exception {

    private Long postId;
}
