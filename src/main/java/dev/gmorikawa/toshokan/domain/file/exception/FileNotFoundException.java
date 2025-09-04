package dev.gmorikawa.toshokan.domain.file.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
        super("File with given ID not found.");
    }
}
