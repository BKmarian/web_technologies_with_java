package lab9example1.demo.exception;

public class NotFoundMovieException extends RuntimeException {

    public NotFoundMovieException(String message) {
        super(message);
    }
}
