package dev.gmorikawa.toshokan.file;

import dev.gmorikawa.toshokan.file.enumerator.FileState;
import dev.gmorikawa.toshokan.file.type.FileType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Column(length = 255)
    private String path;
    
    @Column(length = 255)
    private String filename;

    @JoinColumn(name="type_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private FileType type;

    @Enumerated(EnumType.STRING)
    private FileState state;

    public File() { }

    public File(String id, String path, String filename, FileType type, FileState state) {
        this.id = id;
        this.path = path.trim();
        this.filename = filename.trim();
        this.type = type;
        this.state = state;
    }

    public File(String path, String filename, FileType type, FileState state) {
        this.path = path.trim();
        this.filename = filename.trim();
        this.type = type;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        String trimmedPath = path.trim();
        if (trimmedPath.charAt(trimmedPath.length() - 1) == '/') {
            this.path = trimmedPath;
        } else {
            this.path = trimmedPath + "/";
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename.trim();
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public FileState getState() {
        return state;
    }

    public void setState(FileState state) {
        this.state = state;
    }

    public String getFilePath() {
        return new StringBuilder()
            .append(path)
            .append(filename)
            .toString();
    }
}
