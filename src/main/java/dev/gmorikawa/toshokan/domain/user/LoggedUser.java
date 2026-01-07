package dev.gmorikawa.toshokan.domain.user;

import java.util.UUID;

import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserStatus;

public class LoggedUser {

    private final UUID id;
    private final String username;
    private final String email;
    private final UserRole role;
    private final UserStatus status;
    private final String fullname;

    public LoggedUser(
            UUID id,
            String username,
            String email,
            UserRole role,
            UserStatus status,
            String fullname
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.fullname = fullname;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getFullname() {
        return fullname;
    }

}
