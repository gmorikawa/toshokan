package dev.gmorikawa.toshokan.document.book;

import java.util.List;

import dev.gmorikawa.toshokan.author.Author;
import dev.gmorikawa.toshokan.category.Category;
import dev.gmorikawa.toshokan.document.Document;
import dev.gmorikawa.toshokan.publisher.Publisher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Document {

    @JoinColumn(name = "publisher_id")
    @ManyToOne
    private Publisher publisher;

    @Column(unique = true, length = 31)
    private String isbn;

    public Book() { }

    public Book(String id, String title, Integer year, List<Author> authors, String description, Category category, Publisher publisher, String isbn) {
        super(id, title, year, authors, description, category);

        this.publisher = publisher;
        this.isbn = isbn.trim();
    }

    public Book(String title, Integer year, List<Author> authors, String description, Category category, Publisher publisher, String isbn) {
        super(title, year, authors, description, category);

        this.publisher = publisher;
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn.trim();
    }

    @Override
    public String getFilePath() {
        return new StringBuilder()
            .append(publisher.getName().toLowerCase().replace(' ', '_'))
            .append("/")
            .append(getTitle().toLowerCase().replace(' ', '_'))
            .append("/")
            .toString();
    }
}
