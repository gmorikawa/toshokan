package dev.gmorikawa.toshokan.document.whitepaper;

import dev.gmorikawa.toshokan.category.Category;
import dev.gmorikawa.toshokan.document.Document;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "whitepapers")
public class Whitepaper extends Document {
    
    public Whitepaper() { }

    public Whitepaper(String id, String title, Integer year, String authors, String description, Category category) {
        super(id, title, year, authors, description, category);
    }

    public Whitepaper(String title, Integer year, String authors, String description, Category category) {
        super(title, year, authors, description, category);
    }

    @Override
    public String getFilePath() {
        return new StringBuilder()
            .append(getCategory().getName().toLowerCase().replace(' ', '_'))
            .append("/")
            .append(getId())
            .append("/")
            .toString();
    }
}
