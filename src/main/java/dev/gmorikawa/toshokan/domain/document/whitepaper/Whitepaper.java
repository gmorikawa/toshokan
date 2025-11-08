package dev.gmorikawa.toshokan.domain.document.whitepaper;

import dev.gmorikawa.toshokan.domain.document.Document;
import dev.gmorikawa.toshokan.domain.organization.Organization;
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

}
