package dev.gmorikawa.toshokan.domain.document.book;

import java.time.LocalDate;

import dev.gmorikawa.toshokan.domain.document.Document;
import dev.gmorikawa.toshokan.domain.document.book.enumeration.BookType;
import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Document {

    @Column(unique = true, length = 31)
    private String isbn;

    @JoinColumn(name = "publisher_id")
    @ManyToOne
    private Publisher publisher;

    @Column(nullable = true)
    private LocalDate publishedAt;

    @Enumerated(EnumType.STRING)
    private BookType type;

    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn.trim();
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }
}
