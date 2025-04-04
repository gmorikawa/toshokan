package dev.gmorikawa.toshokan.user.exception;

public class EmailNotAvailableException extends RuntimeException {
    public EmailNotAvailableException() {
        super("Email is already registered");
    }
}
