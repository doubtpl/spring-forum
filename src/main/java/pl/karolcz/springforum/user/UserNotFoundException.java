package pl.karolcz.springforum.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserNotFoundException extends Exception {

    private Long userId;
}
