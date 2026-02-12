package dev.gmorikawa.toshokan.application.rest.dto.user;

import dev.gmorikawa.toshokan.application.rest.dto.file.FileDTO;
import dev.gmorikawa.toshokan.core.user.UserProfile;

public class UserProfileDTO {
    private final String fullname;
    private final String biography;
    private final FileDTO avatar;

    public UserProfileDTO(
        UserProfile profile
    ) {
        this.fullname = profile.getFullname();
        this.biography = profile.getBiography() != null
            ? profile.getBiography()
            : "";
        this.avatar = profile.getAvatar() != null
            ? new FileDTO(profile.getAvatar())
            : null;
    }

    public String getFullname() {
        return fullname;
    }

    public String getBiography() {
        return biography;
    }

    public FileDTO getAvatar() {
        return avatar;
    }
}
