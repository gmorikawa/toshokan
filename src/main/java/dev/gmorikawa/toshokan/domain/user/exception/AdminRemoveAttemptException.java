package dev.gmorikawa.toshokan.domain.user.exception;

public class AdminRemoveAttemptException extends RuntimeException {
    public AdminRemoveAttemptException() {
        super("'ADMIN' user cannot be removed.");
    }
}
