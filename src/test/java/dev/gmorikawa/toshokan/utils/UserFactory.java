package dev.gmorikawa.toshokan.utils;

import java.util.UUID;

import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserStatus;

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
