package pl.karolcz.springforum.user;

public interface UserService {

    User createUser(CreateUpdateUserCommand createUpdateUser);

    User updateUser(Long userId, CreateUpdateUserCommand createUpdateUser) throws UserNotFoundException;

    User getUser(Long userId) throws UserNotFoundException;

    User getUserByUsername(String username) throws UserNotFoundException;

    void deleteUser(Long userId) throws UserNotFoundException;
}
