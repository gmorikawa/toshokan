package dev.gmorikawa.toshokan.utils;

import java.util.UUID;

import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;

public class UserFactory {
    public static User buildAdmin() {
        User admin = new User();
        admin.setId(UUID.randomUUID());
        admin.setEmail("admin@email.com");
        admin.setFullname("Admin Test");
        admin.setRole(UserRole.ADMIN);
        admin.setPassword("hashed");

        return admin;
    }
}
