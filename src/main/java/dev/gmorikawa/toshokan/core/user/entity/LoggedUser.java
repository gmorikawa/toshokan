package dev.gmorikawa.toshokan.core.user.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import dev.gmorikawa.toshokan.core.user.User;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.core.user.enumerator.UserStatus;

public class LoggedUser {

    private final UUID id;
    private final String username;
    private final String email;
    private final UserRole role;
    private final UserStatus status;

    public LoggedUser(
            UUID id,
            String username,
            String email,
            UserRole role,
            UserStatus status
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public LoggedUser(User user) {
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

    public boolean hasRole(UserRole ...roles) {
        Set<UserRole> roleSet = new HashSet<>();
        roleSet.addAll(Arrays.asList(roles));
        return roleSet.contains(getRole());
    }

    public boolean isUserEqual(User user) {
        return this.id.equals(user.getId());
    }

}
