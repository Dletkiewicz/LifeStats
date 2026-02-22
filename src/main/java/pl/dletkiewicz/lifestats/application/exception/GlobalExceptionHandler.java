package pl.dletkiewicz.lifestats.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dletkiewicz.lifestats.application.dto.ErrorResponseDto;
import pl.dletkiewicz.lifestats.domain.exception.InvalidCredentialsException;
import pl.dletkiewicz.lifestats.domain.exception.UserAlreadyExistsException;
import pl.dletkiewicz.lifestats.domain.exception.UserNotExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleLifestatsException(UserAlreadyExistsException ex) {

        ErrorResponseDto response = new ErrorResponseDto(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidCredentials(InvalidCredentialsException ex) {

        ErrorResponseDto response = new ErrorResponseDto(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(response);
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotExistsException(UserNotExistsException ex) {

        ErrorResponseDto response = new ErrorResponseDto(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }
}

