package dev.gmorikawa.toshokan.domain.auth.contract;

import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;

public interface Authorization {
    void checkUserRole(LoggedUser loggedUser, UserRole ...roles);
}