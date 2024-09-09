package az.atl.academy.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseModel> HandleTaskNotFoundException(AlreadyExistsException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "ATL");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers)
                .body(new ErrorResponseModel(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }
}
