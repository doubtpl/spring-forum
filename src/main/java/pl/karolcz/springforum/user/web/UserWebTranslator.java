package pl.karolcz.springforum.user.web;

import pl.karolcz.springforum.user.CreateUpdateUserCommand;
import pl.karolcz.springforum.user.User;

class UserWebTranslator {

    CreateUpdateUserCommand toCreateUpdateCommand(CreateUpdateUserDto createUpdateUserDto) {
        return CreateUpdateUserCommand.builder()
                .username(createUpdateUserDto.getUsername())
                .password(createUpdateUserDto.getPassword())
                .build();
    }

    UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
