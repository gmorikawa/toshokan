package dev.gmorikawa.toshokan.domain.document.book;

import dev.gmorikawa.toshokan.domain.category.Category;
import dev.gmorikawa.toshokan.domain.document.Document;
import dev.gmorikawa.toshokan.domain.document.book.enumerator.BookType;
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

    @Column(length = 225, nullable = true)
    private String subtitle;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "publisher_id")
    @ManyToOne
    private Publisher publisher;

    @Enumerated(EnumType.STRING)
    private BookType type;

    @Column(length = 127)
    private String edition;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String getFileDirectory() {
        return String.format(
            "/%s/%s",
            "books",
            this.getId().toString().replace("-", "")
        );
    }
}
