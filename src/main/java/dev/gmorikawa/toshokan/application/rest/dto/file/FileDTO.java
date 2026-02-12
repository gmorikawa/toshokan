package dev.gmorikawa.toshokan.application.rest.dto.file;

import java.util.UUID;

import dev.gmorikawa.toshokan.core.file.File;
import dev.gmorikawa.toshokan.core.file.enumerator.FileState;

public class FileDTO {
    private final UUID id;
    private final String path;
    private final String filename;
    private final FileTypeDTO type;
    private final FileState state;

    public FileDTO(
        File file
    ) {
        this.id = file.getId();
        this.path = file.getPath();
        this.filename = file.getFilename();
        this.type = new FileTypeDTO(file.getType());
        this.state = file.getState();
    }

    public UUID getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getFilename() {
        return filename;
    }

    public FileTypeDTO getType() {
        return type;
    }

    public FileState getState() {
        return state;
    }
}
