package lab9example1.demo.advice;

import lab9example1.demo.exception.NotFoundMovieException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInvalidMovie(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body("For the filed called '" + e.getFieldError().getField() +
                        "' , its value is not valid -> (" + e.getFieldError().getRejectedValue() +
                        "). The message sent with the validation is: " + e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(NotFoundMovieException.class)
    public ResponseEntity<String> handleNotFoundMovie(NotFoundMovieException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
