package dev.gmorikawa.toshokan.application.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.gmorikawa.toshokan.application.rest.serialization.ExceptionResponseBody;
import dev.gmorikawa.toshokan.domain.auth.exception.InvalidCredentialsException;
import dev.gmorikawa.toshokan.domain.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.domain.category.exception.CategoryNameNotAvailableException;
import dev.gmorikawa.toshokan.domain.file.exception.FileNotFoundException;
import dev.gmorikawa.toshokan.domain.user.exception.AdminRemoveAttemptException;
import dev.gmorikawa.toshokan.domain.user.exception.EmailNotAvailableException;
import dev.gmorikawa.toshokan.domain.user.exception.ForbiddenAdminUpdateException;
import dev.gmorikawa.toshokan.domain.user.exception.UsernameNotAvailableException;
import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final DomainExceptionMetadata[] EXCEPTION_STATUS_CODE_MAPPING = {
        new DomainExceptionMetadata(InvalidCredentialsException.class, HttpStatus.UNAUTHORIZED),
        new DomainExceptionMetadata(UnauthorizedActionException.class, HttpStatus.FORBIDDEN),
        new DomainExceptionMetadata(AdminRemoveAttemptException.class, HttpStatus.FORBIDDEN),
        new DomainExceptionMetadata(EmailNotAvailableException.class, HttpStatus.CONFLICT),
        new DomainExceptionMetadata(ForbiddenAdminUpdateException.class, HttpStatus.FORBIDDEN),
        new DomainExceptionMetadata(UsernameNotAvailableException.class, HttpStatus.CONFLICT),
        new DomainExceptionMetadata(CategoryNameNotAvailableException.class, HttpStatus.CONFLICT),
        new DomainExceptionMetadata(FileNotFoundException.class, HttpStatus.NOT_FOUND),
    };

    private static HttpStatus getHttpStatusForException(DomainException exception) {
        for (DomainExceptionMetadata metadata : EXCEPTION_STATUS_CODE_MAPPING) {
            if (metadata.getExceptionClass().isInstance(exception)) {
                return metadata.getHttpStatus();
            }
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @ExceptionHandler({ 
        EmailNotAvailableException.class,
        UsernameNotAvailableException.class,
        InvalidCredentialsException.class,
        UnauthorizedActionException.class
    })
    public ResponseEntity<ExceptionResponseBody> handleConflict(DomainException exception, WebRequest request) {
        HttpStatus status = getHttpStatusForException(exception);
        ExceptionResponseBody responseBody = new ExceptionResponseBody(status, exception);

        return new ResponseEntity<>(responseBody, status);
    }
}
