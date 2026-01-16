package dev.gmorikawa.toshokan.domain.document.file;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "document_files")
public class DocumentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "document_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Document document;

    @JoinColumn(name = "file_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private File file;

    @Column(nullable = true, length = 4095)
    private String description;

    public DocumentFile() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
