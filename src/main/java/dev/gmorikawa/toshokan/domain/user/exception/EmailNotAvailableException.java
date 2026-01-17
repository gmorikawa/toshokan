package dev.gmorikawa.toshokan.domain.user.exception;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class EmailNotAvailableException extends DomainException {
    public EmailNotAvailableException() {
        super("Email is already registered");
    }
}
