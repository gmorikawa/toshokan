package dev.gmorikawa.toshokan.core.notification.email.exception;

public class MessageSendFailureException extends RuntimeException {
    public MessageSendFailureException() {
        super("Failed to send the email message.");
    }
}
