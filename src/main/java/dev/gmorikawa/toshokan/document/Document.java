package dev.gmorikawa.toshokan.document;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import dev.gmorikawa.toshokan.category.Category;
import dev.gmorikawa.toshokan.topic.Topic;

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

    @JoinColumn()
    @ManyToOne
    private Category category;

    @JoinTable(
        name = "document_topics",
        joinColumns = @JoinColumn(
            name = "document_id",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "topic_id",
            referencedColumnName = "id"
        )
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Topic> topics;

    public Document() {
        topics = new ArrayList<Topic>();
    }

    public Document(String id, String title, Integer year, String authors, String description, Category category) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.authors = authors;
        this.description = description;
        this.category = category;

        topics = new ArrayList<Topic>();
    }

    public Document(String title, Integer year, String authors, String description, Category category) {
        this.title = title;
        this.year = year;
        this.authors = authors;
        this.description = description;
        this.category = category;

        topics = new ArrayList<Topic>();
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void addTopics(Topic topic) {
        this.topics.add(topic);
    }
}