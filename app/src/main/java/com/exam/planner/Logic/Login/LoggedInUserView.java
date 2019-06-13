package com.exam.planner.Logic.Login;

/**
 * Class exposing authenticated user details to the UI.
 * Will add some sort of ID here to easily fetch events probably
 */
class LoggedInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}
