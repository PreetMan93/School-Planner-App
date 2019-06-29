package com.exam.planner.Logic.Login.data;

import com.exam.planner.DSO.User;
import com.exam.planner.Persistence.IUserPersistence;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class DataSource {

    private IUserPersistence db = new UserPersistenceStub();
    //DBSetup db = new DBSetup(); codes broken :)

    public boolean attemptLogin(String username, String password){
        return db.doIExist(username, password);
    }

    public Result<LoggedInUser> login(String username, String password) {
        try {
            LoggedInUser user;
            // Todo This is terrible fix me pls
            if(db.doIExist(username, password)){
                user = new LoggedInUser(username, false);
            } else {
                db.addUser(new User(java.util.UUID.randomUUID().toString(), username, password));
                user = new LoggedInUser(username, true);
            }
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in ", e));
        }
    }

    public Result<LoggedInUser> register(String username, String password, String secretQ, String secretA){
        try {
            LoggedInUser user;
            if(db.doIExist(username, password)) {
                user = new LoggedInUser(username, false);
            } else {
                db.addUser(new User(java.util.UUID.randomUUID().toString(), username, password, secretQ, secretA));
                user = new LoggedInUser(username, true);
            }
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in ", e));
        }
    }



}
