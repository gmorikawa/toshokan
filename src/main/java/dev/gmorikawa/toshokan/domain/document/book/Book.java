package dev.gmorikawa.toshokan.domain.document.book;

import dev.gmorikawa.toshokan.domain.category.Category;
import dev.gmorikawa.toshokan.domain.document.Document;
import dev.gmorikawa.toshokan.domain.document.book.enumeration.BookType;
import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Document {

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "publisher_id")
    @ManyToOne
    private Publisher publisher;

    @Enumerated(EnumType.STRING)
    private BookType type;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }
}
