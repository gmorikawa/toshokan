package dev.gmorikawa.toshokan.application.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.gmorikawa.toshokan.application.rest.serialization.ExceptionResponseBody;
import dev.gmorikawa.toshokan.domain.auth.exception.InvalidCredentialsException;
import dev.gmorikawa.toshokan.domain.user.exception.EmailNotAvailableException;
import dev.gmorikawa.toshokan.domain.user.exception.UsernameNotAvailableException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ 
        EmailNotAvailableException.class,
        UsernameNotAvailableException.class
    })
    public ResponseEntity<ExceptionResponseBody> handleConflict(RuntimeException ex, WebRequest request) {
        ExceptionResponseBody responseBody = new ExceptionResponseBody(HttpStatus.CONFLICT, ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
        InvalidCredentialsException.class
    })
    public ResponseEntity<ExceptionResponseBody> handleAuthorization(RuntimeException ex, WebRequest request) {
        ExceptionResponseBody responseBody = new ExceptionResponseBody(HttpStatus.UNAUTHORIZED, ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }
}