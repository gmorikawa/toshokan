package dev.gmorikawa.toshokan.app.web.auth;

import jakarta.servlet.http.Cookie;

public class AuthorizationCookie extends Cookie {
    public AuthorizationCookie(String token) {
        super("Authorization", token);

        this.setPath("/");
    }
}
