package dev.gmorikawa.toshokan.unit.user;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserStatus;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class UserRemoveTest extends UserTestEnvironment {

    @Test
    public void testRemoveUser() {
        // Mock a admin user that will handle this action
        User admin = UserFactory.buildAdmin();

        // Create a new user and persist it in the database
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("password");
        user.setEmail("john.doe@email.com");
        user.setRole(UserRole.READER);
        user.setFullname("John Doe");
        User savedUser = service.create(admin, user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        // assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUser.getRole()).isEqualTo(user.getRole());
        assertThat(savedUser.getFullname()).isEqualTo(user.getFullname());
        assertThat(savedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);

        // Remove the author and check if it still exists in the database
        service.remove(admin, savedUser.getId());
        User searchedUser = service.getById(admin, savedUser.getId());
        assertThat(searchedUser).isNull();
    }
}
