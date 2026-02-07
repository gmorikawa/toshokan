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
public class UserUpdateTest extends UserTestEnvironment {

    @Test
    public void testUpdateUser() {
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

        // Modify the created user apply the modifications
        savedUser.setEmail("joe.don@email.com");
        savedUser.setUsername("joedon");
        User updatedUser = service.update(admin, savedUser.getId(), savedUser);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getUsername()).isEqualTo(savedUser.getUsername());
        assertThat(updatedUser.getEmail()).isEqualTo(savedUser.getEmail());

        // clean-up
        clean(updatedUser);
    }

    @Test
    public void testBlockAndReactivateUser() {
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

        // Block the user
        User blockedUser = service.block(savedUser.getId());
        assertThat(blockedUser.getStatus()).isEqualTo(UserStatus.BLOCKED);

        // Reactivated the user
        User reactivatedUser = service.activate(blockedUser.getId());
        assertThat(reactivatedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
       
        // clean-up
        clean(reactivatedUser);
    }
}
