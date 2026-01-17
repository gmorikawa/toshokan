package dev.gmorikawa.toshokan.domain.auth.exception;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class InvalidCredentialsException extends DomainException {
    public InvalidCredentialsException() {
        super("Username and/or password are incorrect.");
    }
}
