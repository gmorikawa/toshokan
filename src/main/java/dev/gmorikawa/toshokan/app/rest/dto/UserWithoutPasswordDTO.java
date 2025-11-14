package dev.gmorikawa.toshokan.app.rest.dto;

import java.util.UUID;

import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserStatus;

public class UserWithoutPasswordDTO {
    private final UUID id;
    private final String username;
    private final String email;
    private final UserRole role;
    private final UserStatus status;
    private final String fullname;

    public UserWithoutPasswordDTO (User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.fullname = user.getFullname();
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
