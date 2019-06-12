package com.exam.planner.Logic.login.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private boolean firstLogin;

    public LoggedInUser(String userId, String displayName, boolean firstLogin) {
        this.userId = userId;
        this.displayName = displayName;
        this.firstLogin = firstLogin;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isFirstLogin() { return firstLogin; }
    public void notNewUser(){ firstLogin = false;}
}
