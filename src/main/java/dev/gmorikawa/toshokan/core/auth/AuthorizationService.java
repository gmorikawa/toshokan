package dev.gmorikawa.toshokan.core.auth;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.core.auth.contract.Authorization;
import dev.gmorikawa.toshokan.core.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;

@Service
public class AuthorizationService implements Authorization {
    @Override
    public void checkUserRole(LoggedUser loggedUser, UserRole ...roles) {
        if (!loggedUser.hasRole(roles)) {
            throw new UnauthorizedActionException();
        }
    }
}
