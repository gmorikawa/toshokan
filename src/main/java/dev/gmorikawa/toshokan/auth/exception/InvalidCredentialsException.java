package dev.gmorikawa.toshokan.auth.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Username and/or password are incorrect.");
    }
}
