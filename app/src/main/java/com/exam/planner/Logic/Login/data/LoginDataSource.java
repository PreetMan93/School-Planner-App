package com.exam.planner.Logic.Login.data;

import com.exam.planner.Logic.Dao.DBSetup;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    //DBSetup db = new DBSetup(); codes broken :)

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            // TODO: Make sure firstLogin can become false if we find there information in the DB
            LoggedInUser user;
            if(username.equals("username") && password.equals("password")){
                user = new LoggedInUser(java.util.UUID.randomUUID().toString(), username, false);
            }
            else{
                user = new LoggedInUser(java.util.UUID.randomUUID().toString(), username, true);
            }
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

}
