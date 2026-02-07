package dev.gmorikawa.toshokan.application.rest.dto;

import java.util.UUID;

import dev.gmorikawa.toshokan.core.user.User;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.core.user.enumerator.UserStatus;

public class UserWithoutPasswordDTO {
    private final UUID id;
    private final String username;
    private final String email;
    private final UserRole role;
    private final UserStatus status;

    public UserWithoutPasswordDTO (User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.status = user.getStatus();
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
}
