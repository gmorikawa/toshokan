package dev.gmorikawa.toshokan.application.rest.handlers;

import org.springframework.http.HttpStatus;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class DomainExceptionMetadata {
    private final Class<? extends DomainException> exceptionClass;
    private final HttpStatus httpStatus;

    public DomainExceptionMetadata(Class<? extends DomainException> exceptionClass, HttpStatus httpStatus) {
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }
    
    public Class<? extends DomainException> getExceptionClass() {
        return exceptionClass;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}