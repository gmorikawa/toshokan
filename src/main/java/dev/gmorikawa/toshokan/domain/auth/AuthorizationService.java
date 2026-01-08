package dev.gmorikawa.toshokan.domain.auth;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.domain.auth.contract.Authorization;
import dev.gmorikawa.toshokan.domain.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;

@Service
public class AuthorizationService implements Authorization {
    @Override
    public void checkUserRole(LoggedUser loggedUser, UserRole ...roles) {
        if (!loggedUser.hasRole(roles)) {
            throw new UnauthorizedActionException();
        }
    }
}
