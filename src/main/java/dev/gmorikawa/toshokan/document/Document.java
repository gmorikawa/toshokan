package dev.gmorikawa.toshokan.document;

import dev.gmorikawa.toshokan.category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private Integer year;

    private String authors;

    private String description;

    @JoinColumn(name = "categoryId")
    @ManyToOne
    private Category category;

    public Document() { }

    public Document(String id, String title, Integer year, String authors, String description, Category category) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.authors = authors;
        this.description = description;
        this.category = category;
    }

    public Document(String title, Integer year, String authors, String description, Category category) {
        this.title = title;
        this.year = year;
        this.authors = authors;
        this.description = description;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}