package dev.gmorikawa.toshokan.user;

import dev.gmorikawa.toshokan.user.enumerator.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    private String fullname;

    public User() { }

    public User(String id, String username, String password, String email, UserRole role, String fullname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.fullname = fullname;
    }

    public User(String username, String password, String email, UserRole role, String fullname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}