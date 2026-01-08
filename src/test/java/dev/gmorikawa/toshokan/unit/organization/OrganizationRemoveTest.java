package dev.gmorikawa.toshokan.unit.organization;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.organization.Organization;
import dev.gmorikawa.toshokan.domain.organization.enumerator.OrganizationType;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class OrganizationRemoveTest extends OrganizationTestEnvironment {

    @Test
    public void testRemoveOrganization() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Organization organization = new Organization();
        organization.setName("Google");
        organization.setDescription("A technology company");
        organization.setType(OrganizationType.COMPANY);
        
        Organization savedOrganization = service.create(admin, organization);

        assertThat(savedOrganization).isNotNull();
        assertThat(savedOrganization.getName()).isEqualTo(organization.getName());
        assertThat(savedOrganization.getDescription()).isEqualTo(organization.getDescription());
        assertThat(savedOrganization.getType()).isEqualTo(organization.getType());

        service.remove(admin, savedOrganization.getId());

        Organization searchedOrganization = service.getById(savedOrganization.getId());
        assertThat(searchedOrganization).isNull();
    }
}