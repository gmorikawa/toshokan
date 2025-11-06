package dev.gmorikawa.toshokan.domain.user.exception;

public class UsernameNotAvailableException extends RuntimeException {
    public UsernameNotAvailableException() {
        super("Username is already registered");
    }
}
