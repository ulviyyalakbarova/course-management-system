package az.atl.academy.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseModel> handleTaskNotFoundException(AlreadyExistsException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ATL");
        return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers)
                .body(new ErrorResponseModel(HttpStatus.CONFLICT.value(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseModel> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }

        ErrorResponseModel errorResponse = new ErrorResponseModel(
                HttpStatus.BAD_REQUEST.value(),
                String.join(", ", errorMessages)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseModel> handleTeacherNotFoundException(UserNotFoundException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ATL");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers)
                .body(new ErrorResponseModel(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseModel> handleCourseNotFoundException(CourseNotFoundException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ATL");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers)
                .body(new ErrorResponseModel(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = ExamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseModel> handleExamNotFoundException(ExamNotFoundException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ATL");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers)
                .body(new ErrorResponseModel(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = SemesterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseModel> handleSemesterNotFoundException(SemesterNotFoundException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ATL");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers)
                .body(new ErrorResponseModel(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = ExamNotFinishedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseModel> handleExamNotFinishedException(ExamNotFinishedException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ATL");
        return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers)
                .body(new ErrorResponseModel(HttpStatus.CONFLICT.value(), exception.getMessage()));
    }
}
