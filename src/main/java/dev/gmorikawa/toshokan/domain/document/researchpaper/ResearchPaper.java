package dev.gmorikawa.toshokan.domain.document.researchpaper;

import dev.gmorikawa.toshokan.domain.document.Document;
import dev.gmorikawa.toshokan.domain.organization.Organization;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "research_papers")
public class ResearchPaper extends Document {

    @JoinColumn(name = "organization_id")
    @ManyToOne
    private Organization organization;

    @Column(length = 255, nullable = true)
    private String keywords;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String getFileDirectory() {
        return String.format(
            "/%s/%s",
            "researchpapers",
            this.getId().toString().replace("-", "")
        );
    }
}
