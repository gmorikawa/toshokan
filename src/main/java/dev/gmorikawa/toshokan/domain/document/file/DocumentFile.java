package dev.gmorikawa.toshokan.domain.document.file;

import dev.gmorikawa.toshokan.domain.document.Document;
import dev.gmorikawa.toshokan.domain.file.File;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DocumentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JoinColumn(
            name = "document_id",
            referencedColumnName = "id"
    )
    @ManyToOne
    private Document document;

    @JoinColumn(
            name = "file_id",
            referencedColumnName = "id"
    )
    @ManyToOne
    private File file;

    @Column(nullable = true, length = 4095)
    private String description;

    public DocumentFile() {
    }

    public DocumentFile(String id, Document document, File file, String description) {
        this.id = id;
        this.document = document;
        this.file = file;
        this.description = description;
    }

    public DocumentFile(Document document, File file, String description) {
        this.document = document;
        this.file = file;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
