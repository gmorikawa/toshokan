package dev.gmorikawa.toshokan.core.notification.email.exception;

public class InvalidEmailAddressException extends RuntimeException {
    public InvalidEmailAddressException() {
        super("Invalid email address provided.");
    }
}
