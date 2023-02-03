package io.modicon.utilsmodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiExceptionDto> handleApiRequestException(ApiException e) {
        return new ResponseEntity<>(new ApiExceptionDto(e.getMessage()), e.getStatus());
    }

    /**
     * Spring validation exception handling
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionDto> handleApiRequestValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ApiExceptionDto(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()),
                HttpStatus.BAD_REQUEST);
    }

}