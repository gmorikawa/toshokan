package dev.gmorikawa.toshokan.domain.user.exception;

public class ForbiddenAdminUpdateException extends RuntimeException {
    public ForbiddenAdminUpdateException(String description) {
        super("Forbidden update on admin: " + description);
    }

    public ForbiddenAdminUpdateException() {
        super("Forbidden update on admin: ");
    }
}
