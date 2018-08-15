package pl.karolcz.springforum.user.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.karolcz.springforum.user.CreateUpdateUserCommand;
import pl.karolcz.springforum.user.UserNotFoundException;
import pl.karolcz.springforum.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
class UserController {
    private final UserService userService;
    private final UserWebTranslator webTranslator;

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return webTranslator.toDto(userService.getUser(userId));
    }

    @GetMapping
    public UserDto getUserByUserName(@RequestParam("username") String username) throws UserNotFoundException {
        return webTranslator.toDto(userService.getUserByUsername(username));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@Validated @RequestBody CreateUpdateUserDto createUpdateUserDto) {
        CreateUpdateUserCommand createUpdateUser = webTranslator.toCreateUpdateCommand(createUpdateUserDto);
        return webTranslator.toDto(userService.createUser(createUpdateUser));
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@PathVariable("userId") Long userId, @Validated @RequestBody CreateUpdateUserDto createUpdateUserDto) throws UserNotFoundException {
        CreateUpdateUserCommand createUpdateUser = webTranslator.toCreateUpdateCommand(createUpdateUserDto);
        return webTranslator.toDto(userService.updateUser(userId, createUpdateUser));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
    }

}
