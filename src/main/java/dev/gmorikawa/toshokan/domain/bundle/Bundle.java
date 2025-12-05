package dev.gmorikawa.toshokan.domain.bundle;

import java.util.List;
import java.util.UUID;

import dev.gmorikawa.toshokan.domain.document.Document;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bundles")
public class Bundle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, length = 127)
    private String title;

    @Column(length = 4095)
    private String description;

    @JoinTable(
            name = "bundle_documents",
            joinColumns = @JoinColumn(name = "bundle_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id")
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Document> documents;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return title;
    }
}
