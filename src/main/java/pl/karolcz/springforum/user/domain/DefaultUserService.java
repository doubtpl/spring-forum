package pl.karolcz.springforum.user.domain;

import org.springframework.transaction.annotation.Transactional;

import pl.karolcz.springforum.user.CreateUpdateUserCommand;
import pl.karolcz.springforum.user.User;
import pl.karolcz.springforum.user.UserNotFoundException;
import pl.karolcz.springforum.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final UserDomainTranslator domainTranslator;

    @Override
    @Transactional
    public User createUser(CreateUpdateUserCommand createUpdateUserCommand) {
        User newUser = domainTranslator.toNewUser(createUpdateUserCommand);
        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public User updateUser(Long userId, CreateUpdateUserCommand updateUserCommand) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setUsername(updateUserCommand.getUsername());
        user.setPassword(updateUserCommand.getPassword());
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) throws UserNotFoundException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);
    }
}