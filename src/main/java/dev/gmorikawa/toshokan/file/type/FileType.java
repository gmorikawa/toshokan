package dev.gmorikawa.toshokan.file.type;

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
    private String id;

    @Column(length = 127)
    private String name;

    @Column(unique = true, length = 15)
    private String extension;

    public FileType() { }

    public FileType(String id, String name, String extension) {
        this.id = id;
        this.name = name.trim();
        this.extension = extension.trim();
    }

    public FileType(String name, String extension) {
        this.name = name.trim();
        this.extension = extension.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
