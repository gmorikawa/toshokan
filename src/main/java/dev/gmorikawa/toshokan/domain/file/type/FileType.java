package dev.gmorikawa.toshokan.domain.file.type;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "file_types")
public class FileType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 127)
    private String name;

    @Column(unique = true, length = 15)
    private String extension;

    @Column(length = 127)
    private String mimeType;

    public FileType() { }

    public FileType(UUID id, String name, String extension) {
        this.id = id;
        this.name = name.trim();
        this.extension = extension.trim();
    }

    public FileType(String name, String extension) {
        this.name = name.trim();
        this.extension = extension.trim();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension.trim();
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType.trim();
    }
}
