package pl.karolcz.springforum.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import pl.karolcz.springforum.post.PostNotFoundException;
import pl.karolcz.springforum.user.UserNotFoundException;

@ControllerAdvice(annotations = RestController.class)
class DomainExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handle(UserNotFoundException ex) {
        return new ResponseEntity<>(ErrorDto.builder()
                .message("User is not found")
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorDto> handle(PostNotFoundException ex) {
        return new ResponseEntity<>(ErrorDto.builder()
                .message("Post is not found")
                .build(), HttpStatus.NOT_FOUND);
    }

}
