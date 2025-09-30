package dev.gmorikawa.toshokan.domain.document.file;

import dev.gmorikawa.toshokan.domain.document.Document;
import dev.gmorikawa.toshokan.domain.file.File;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Document document;

    @JoinColumn(
            name = "file_id",
            referencedColumnName = "id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

    @Column(nullable = true, length = 4095)
    private String label;

    public DocumentFile() {
    }

    public DocumentFile(String id, Document document, File file, String label) {
        this.id = id;
        this.document = document;
        this.file = file;
        this.label = label;
    }

    public DocumentFile(Document document, File file, String label) {
        this.document = document;
        this.file = file;
        this.label = label;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
