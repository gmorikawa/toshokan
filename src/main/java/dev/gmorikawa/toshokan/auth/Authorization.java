package dev.gmorikawa.toshokan.auth;

import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;

public interface Authorization {
    void checkUserRole(User user, UserRole ...roles);
}