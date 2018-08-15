package pl.karolcz.springforum.user.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.karolcz.springforum.user.UserService;

@Configuration
class UserWebConfiguration {

    @Bean
    UserController userController(UserService userService) {
        return new UserController(userService, new UserWebTranslator());
    }
}
