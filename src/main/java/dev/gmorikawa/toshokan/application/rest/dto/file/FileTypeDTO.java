package dev.gmorikawa.toshokan.application.rest.dto.file;

import dev.gmorikawa.toshokan.core.file.type.FileType;

public class FileTypeDTO {

    private final String name;
    private final String extension;
    private final String mimeType;

    public FileTypeDTO(
            FileType fileType
    ) {
        this.name = fileType.getName();
        this.extension = fileType.getExtension();
        this.mimeType = fileType.getMimeType();
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getMimeType() {
        return mimeType;
    }
}
