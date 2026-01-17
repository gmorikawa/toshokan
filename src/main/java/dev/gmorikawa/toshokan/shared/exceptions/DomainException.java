package dev.gmorikawa.toshokan.shared.exceptions;

import java.time.LocalDateTime;

public class DomainException extends RuntimeException {
    private final LocalDateTime timestamp;

    public DomainException(String message) {
        super(message);

        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
