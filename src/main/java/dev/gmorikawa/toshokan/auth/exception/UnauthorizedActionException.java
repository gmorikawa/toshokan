package dev.gmorikawa.toshokan.auth.exception;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException() {
        super("User not authorized to execute action.");
    }
}
