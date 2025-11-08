package dev.gmorikawa.toshokan.domain.document.file;

import java.util.UUID;

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
    private UUID id;

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

    @Column(length = 127)
    private String version;

    @Column(nullable = true, length = 4095)
    private String description;

    @Column(name = "publishing_year", nullable = true)
    private Integer publishingYear;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Integer publishingYear) {
        this.publishingYear = publishingYear;
    }
}
