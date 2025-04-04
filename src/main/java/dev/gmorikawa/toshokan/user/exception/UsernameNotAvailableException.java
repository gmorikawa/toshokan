package dev.gmorikawa.toshokan.user.exception;

public class UsernameNotAvailableException extends RuntimeException {
    public UsernameNotAvailableException() {
        super("Username is already registered");
    }
}
