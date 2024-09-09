package az.atl.academy.exception;

public class AlreadyExistsException extends RuntimeException {
    private String message;

    public AlreadyExistsException() {

    }
    public AlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
