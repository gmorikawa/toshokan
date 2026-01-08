package dev.gmorikawa.toshokan.domain.user.entity;

public class UserSession {

    private final LoggedUser loggedUser;
    private final String token;

    public UserSession(LoggedUser loggedUser, String token) {
        this.loggedUser = loggedUser;
        this.token = token;
    }

    public LoggedUser getLoggedUser() {
        return loggedUser;
    }

    public String getToken() {
        return token;
    }

}
