package dev.gmorikawa.toshokan.unit.organization;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.organization.Organization;
import dev.gmorikawa.toshokan.core.organization.enumerator.OrganizationType;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class OrganizationUpdateTest extends OrganizationTestEnvironment {

    @Test
    public void testUpdateOrganization() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Organization organization = new Organization();
        organization.setName("MIT");
        organization.setDescription("Massachusetts Institute of Technology");
        organization.setType(OrganizationType.UNIVERSITY);
        
        Organization savedOrganization = service.create(admin, organization);

        assertThat(savedOrganization).isNotNull();
        assertThat(savedOrganization.getName()).isEqualTo(organization.getName());
        assertThat(savedOrganization.getDescription()).isEqualTo(organization.getDescription());
        assertThat(savedOrganization.getType()).isEqualTo(organization.getType());

        savedOrganization.setName("Google");
        savedOrganization.setDescription("A technology company");
        savedOrganization.setType(OrganizationType.COMPANY);
        
        Organization updatedOrganization = service.update(admin, savedOrganization.getId(), savedOrganization);

        assertThat(updatedOrganization).isNotNull();
        assertThat(updatedOrganization.getName()).isEqualTo(savedOrganization.getName());
        assertThat(updatedOrganization.getDescription()).isEqualTo(savedOrganization.getDescription());
        assertThat(updatedOrganization.getType()).isEqualTo(savedOrganization.getType());

        // clean-up
        clean(updatedOrganization);
    }
}