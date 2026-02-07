package dev.gmorikawa.toshokan.core.user;

import java.util.UUID;

import dev.gmorikawa.toshokan.core.file.File;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @Column(name = "user_id")
    private UUID id;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 127)
    private String fullname;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @JoinColumn(
        name = "avatar_id",
        referencedColumnName = "id"
    )
    @ManyToOne(fetch = FetchType.EAGER)
    private File avatar;

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }
}
