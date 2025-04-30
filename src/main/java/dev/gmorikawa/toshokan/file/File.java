package dev.gmorikawa.toshokan.file;

import dev.gmorikawa.toshokan.file.type.FileType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String filename;

    @JoinColumn(name="type_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private FileType type;

    public File() { }

    public File(String id, String filename, FileType type) {
        this.id = id;
        this.filename = filename;
        this.type = type;
    }

    public File(String filename, FileType type) {
        this.filename = filename;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }
}
