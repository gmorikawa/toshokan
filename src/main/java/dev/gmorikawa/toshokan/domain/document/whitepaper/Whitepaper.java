package dev.gmorikawa.toshokan.domain.document.whitepaper;

import java.time.LocalDate;

import dev.gmorikawa.toshokan.domain.document.Document;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "whitepapers")
public class Whitepaper extends Document {

    @Column(nullable = true)
    private LocalDate publishedAt;

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    // @Override
    // public String getFilePath() {
    //     return new StringBuilder()
    //         .append(getCategory().getName().toLowerCase().replace(' ', '_'))
    //         .append("/")
    //         .append(getId())
    //         .append("/")
    //         .toString();
    // }
}
