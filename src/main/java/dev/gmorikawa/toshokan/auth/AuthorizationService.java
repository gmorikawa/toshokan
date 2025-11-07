package dev.gmorikawa.toshokan.auth;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;

@Service
public class AuthorizationService implements Authorization {
    @Override
    public void checkUserRole(User user, UserRole ...roles) {
        if (!user.hasRole(roles)) {
            throw new UnauthorizedActionException();
        }
    }
}
