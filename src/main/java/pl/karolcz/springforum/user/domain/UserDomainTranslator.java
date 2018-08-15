package pl.karolcz.springforum.user.domain;

import pl.karolcz.springforum.user.CreateUpdateUserCommand;
import pl.karolcz.springforum.user.User;

class UserDomainTranslator {

    User toNewUser(CreateUpdateUserCommand createUpdateUserCommand) {
        return User.builder()
                .username(createUpdateUserCommand.getUsername())
                .password(createUpdateUserCommand.getPassword())
                .build();
    }
}
