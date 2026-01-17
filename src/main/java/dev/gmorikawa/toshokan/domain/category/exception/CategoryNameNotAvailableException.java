package dev.gmorikawa.toshokan.domain.category.exception;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class CategoryNameNotAvailableException extends DomainException {
    public CategoryNameNotAvailableException() {
        super("Category name is already registered");
    }
}
