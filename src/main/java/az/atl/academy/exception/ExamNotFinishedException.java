package az.atl.academy.exception;

public class ExamNotFinishedException extends RuntimeException {
    public ExamNotFinishedException(String message) {
        super(message);
    }
}
