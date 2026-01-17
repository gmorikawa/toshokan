package dev.gmorikawa.toshokan.domain.auth.exception;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class UnauthorizedActionException extends DomainException {
    public UnauthorizedActionException() {
        super("User not authorized to execute action.");
    }
}
