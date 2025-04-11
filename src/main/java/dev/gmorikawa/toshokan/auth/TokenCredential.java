package dev.gmorikawa.toshokan.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenCredential {
    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("refreshToken")
    private String refreshToken;

    public String getAccessToken() {
        StringBuilder builder = new StringBuilder();
        return builder.append("Bearer ").append(accessToken).toString();
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
