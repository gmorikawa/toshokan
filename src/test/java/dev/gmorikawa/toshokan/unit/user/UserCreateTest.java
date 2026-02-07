package dev.gmorikawa.toshokan.unit.user;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.user.User;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.core.user.enumerator.UserStatus;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class UserCreateTest extends UserTestEnvironment {

    @Test
    public void testCreateAuthor() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        // Create a new user and persist it in the database
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("password");
        user.setEmail("john.doe@email.com");
        user.setRole(UserRole.READER);
        User savedUser = service.create(admin, user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        // assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUser.getRole()).isEqualTo(user.getRole());
        assertThat(savedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);

        // clean-up
        clean(savedUser);
    }
}
