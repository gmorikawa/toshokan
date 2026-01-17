package dev.gmorikawa.toshokan.domain.user.exception;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class AdminRemoveAttemptException extends DomainException {
    public AdminRemoveAttemptException() {
        super("'ADMIN' user cannot be removed.");
    }
}
