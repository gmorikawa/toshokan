package dev.gmorikawa.toshokan.domain.user.exception;

public class EmailNotAvailableException extends RuntimeException {
    public EmailNotAvailableException() {
        super("Email is already registered");
    }
}
