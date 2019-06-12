package com.exam.planner.Logic.login.data;

import com.exam.planner.Logic.login.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            // TODO: Make sure firstLogin can become false if we find there information in the DB
            boolean firstLogin = true;
            // import dbManagerClass
            // Use that class to validate username and password and return a LoggedInUser Object


            LoggedInUser user =
                    new LoggedInUser(java.util.UUID.randomUUID().toString(), username, firstLogin);
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

}
