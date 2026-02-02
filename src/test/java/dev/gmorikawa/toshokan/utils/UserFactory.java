package dev.gmorikawa.toshokan.utils;

import java.util.UUID;

import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.core.user.enumerator.UserStatus;

public class UserFactory {
    public static LoggedUser buildAdmin() {
        return new LoggedUser(
            UUID.randomUUID(),
            "admin",
            "admin@email.com",
            UserRole.ADMIN,
            UserStatus.ACTIVE,
            "Admin Test"
        );
    }
}
