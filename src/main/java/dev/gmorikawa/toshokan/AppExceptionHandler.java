package dev.gmorikawa.toshokan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.gmorikawa.toshokan.auth.exception.InvalidCredentialsException;
import dev.gmorikawa.toshokan.user.exception.EmailNotAvailableException;
import dev.gmorikawa.toshokan.user.exception.UsernameNotAvailableException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ 
        EmailNotAvailableException.class,
        UsernameNotAvailableException.class
    })
    ResponseEntity<ExceptionResponseBody> handleConflict(RuntimeException ex, WebRequest request) {
        ExceptionResponseBody responseBody = new ExceptionResponseBody(HttpStatus.CONFLICT, ex.getMessage());

        return new ResponseEntity<ExceptionResponseBody>(responseBody, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
        InvalidCredentialsException.class
    })
    ResponseEntity<ExceptionResponseBody> handleAuthorization(RuntimeException ex, WebRequest request) {
        ExceptionResponseBody responseBody = new ExceptionResponseBody(HttpStatus.UNAUTHORIZED, ex.getMessage());

        return new ResponseEntity<ExceptionResponseBody>(responseBody, HttpStatus.UNAUTHORIZED);
    }
}