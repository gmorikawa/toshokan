package dev.gmorikawa.toshokan.domain.category.exception;

public class CategoryNameNotAvailableException extends RuntimeException {
    public CategoryNameNotAvailableException() {
        super("Category name is already registered");
    }
}
