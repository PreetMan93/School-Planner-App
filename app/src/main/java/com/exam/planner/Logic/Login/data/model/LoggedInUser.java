package com.exam.planner.Logic.Login.data.model;

import com.exam.planner.DSO.Planner;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String displayName;
    private String id;
    private boolean firstLogin;

    private Planner planner;

    public LoggedInUser(String displayName, String id, boolean firstLogin, Planner planner) {
        this.displayName = displayName;
        this.id = id;
        this.firstLogin = firstLogin;
        this.planner = planner;
    }


    public String getDisplayName() {
        return displayName;
    }

    public boolean isFirstLogin() { return firstLogin; }
    public void notNewUser(){ firstLogin = false;}
    public Planner getPlanner(){ return planner; }

    public String getId() { return id; }
}
