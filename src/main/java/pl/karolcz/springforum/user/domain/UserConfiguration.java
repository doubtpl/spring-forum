package pl.karolcz.springforum.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.karolcz.springforum.user.UserService;

@Configuration
class UserConfiguration {

    @Bean
    UserService userService(UserRepository userRepository) {
        return new DefaultUserService(userRepository, new UserDomainTranslator());
    }

}
