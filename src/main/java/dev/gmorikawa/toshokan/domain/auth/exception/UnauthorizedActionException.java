package dev.gmorikawa.toshokan.domain.auth.exception;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException() {
        super("User not authorized to execute action.");
    }
}
