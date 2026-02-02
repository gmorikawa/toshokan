package dev.gmorikawa.toshokan.core.document.whitepaper;

import dev.gmorikawa.toshokan.core.document.Document;
import dev.gmorikawa.toshokan.core.organization.Organization;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "whitepapers")
public class Whitepaper extends Document {

    @JoinColumn(name = "organization_id")
    @ManyToOne
    private Organization organization;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String getFileDirectory() {
        return String.format(
            "/%s/%s",
            "whitepapers",
            this.getId().toString().replace("-", "")
        );
    }
}
