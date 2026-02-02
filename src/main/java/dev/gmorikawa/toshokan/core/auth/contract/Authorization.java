package dev.gmorikawa.toshokan.core.auth.contract;

import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;

public interface Authorization {
    void checkUserRole(LoggedUser loggedUser, UserRole ...roles);
}