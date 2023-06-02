package SSU.SSUtudyWith.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleEntityNouFound(EntityNotFoundException e) {
        ApiExceptionResponse response = ApiExceptionResponse.fail(401, HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiExceptionResponse> handleIllegalArgument(IllegalArgumentException e) {
        ApiExceptionResponse response = ApiExceptionResponse.fail(401, HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> handleMethodArgument(MethodArgumentNotValidException e) {
        FieldError error = e.getFieldErrors().get(0);
        ApiExceptionResponse response = ApiExceptionResponse.fail(401, error.getField(), HttpStatus.BAD_REQUEST, error.getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handlAllException(Exception e) {
        ApiExceptionResponse response = ApiExceptionResponse.fail(500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
