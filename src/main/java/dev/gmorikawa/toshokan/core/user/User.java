package dev.gmorikawa.toshokan.core.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.core.user.enumerator.UserStatus;
import dev.gmorikawa.toshokan.core.user.exception.ForbiddenAdminUpdateException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, length = 127)
    private String username;

    @Column(length = 255)
    private String password;

    @Column(unique = true, length = 127)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.READER;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserProfile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserConfiguration configuration;

    public User() { }

    public User(UUID id, String username, String password, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String username, String password, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public UserConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(UserConfiguration configuration) {
        this.configuration = configuration;
    }

    public boolean hasRole(UserRole ...roles) {
        Set<UserRole> roleSet = new HashSet<>();
        roleSet.addAll(Arrays.asList(roles));
        return roleSet.contains(getRole());
    }

    public boolean compareId(User user) {
        return user.getId().equals(id);
    }

    public boolean isIdEqual(User user) {
        return user.getId().equals(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public void block() {
        if (role == UserRole.ADMIN) {
            throw new ForbiddenAdminUpdateException("Admin's status cannot be set blocked.");
        }

        this.status = UserStatus.BLOCKED;
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
    }
}
