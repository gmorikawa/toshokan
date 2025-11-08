package dev.gmorikawa.toshokan.unit.organization;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.organization.Organization;
import dev.gmorikawa.toshokan.domain.organization.enumerator.OrganizationType;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class OrganizationCreateTest extends OrganizationTestEnvironment {

    @Test
    public void testCreateOrganization() {
        // Mock a admin user that will handle this action
        User admin = UserFactory.buildAdmin();

        Organization organization = new Organization();

        String name = "MIT";
        String description = "Massachusetts Institute of Technology";
        OrganizationType type = OrganizationType.UNIVERSITY;

        organization.setName(name);
        organization.setDescription(description);
        organization.setType(type);

        Organization savedOrganization = service.create(admin, organization);

        assertThat(savedOrganization).isNotNull();
        assertThat(savedOrganization.getName()).isEqualTo(organization.getName());
        assertThat(savedOrganization.getDescription()).isEqualTo(organization.getDescription());
        assertThat(savedOrganization.getType()).isEqualTo(organization.getType());

        // clean-up
        clean(savedOrganization);
    }
}