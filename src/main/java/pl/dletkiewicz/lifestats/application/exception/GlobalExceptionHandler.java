package pl.dletkiewicz.lifestats.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dletkiewicz.lifestats.application.dto.ErrorResponseDto;
import pl.dletkiewicz.lifestats.domain.exception.UserAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleLifestatsException(UserAlreadyExistsException ex) {

        ErrorResponseDto response = new ErrorResponseDto(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(response);
    }
}

