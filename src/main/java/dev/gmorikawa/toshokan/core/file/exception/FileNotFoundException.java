package dev.gmorikawa.toshokan.core.file.exception;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class FileNotFoundException extends DomainException {
    public FileNotFoundException() {
        super("File with given ID not found.");
    }
}
