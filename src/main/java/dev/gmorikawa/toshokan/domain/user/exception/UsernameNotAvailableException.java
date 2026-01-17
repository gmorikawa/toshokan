package dev.gmorikawa.toshokan.domain.user.exception;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class UsernameNotAvailableException extends DomainException {
    public UsernameNotAvailableException() {
        super("Username is already registered");
    }
}
