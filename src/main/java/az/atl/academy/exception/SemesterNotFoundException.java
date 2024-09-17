package az.atl.academy.exception;

public class SemesterNotFoundException extends RuntimeException {
    public SemesterNotFoundException(String message) {
        super(message);
    }
}
