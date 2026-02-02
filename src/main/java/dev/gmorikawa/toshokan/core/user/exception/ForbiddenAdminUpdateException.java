package dev.gmorikawa.toshokan.core.user.exception;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class ForbiddenAdminUpdateException extends DomainException {
    public ForbiddenAdminUpdateException(String description) {
        super("Forbidden update on admin: " + description);
    }

    public ForbiddenAdminUpdateException() {
        super("Forbidden update on admin: ");
    }
}
