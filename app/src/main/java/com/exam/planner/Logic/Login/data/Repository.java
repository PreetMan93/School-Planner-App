package com.exam.planner.Logic.Login.data;

import com.exam.planner.Logic.Login.LoginFailureException;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class Repository {

    private static volatile Repository instance;

    private DataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private Repository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Repository(DataSource dataSource, boolean test){ this.dataSource = dataSource; }

    public static Repository getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new Repository(dataSource);
        }
        return instance;
    }

    public void notNew(){
        if(user != null){
            user.notNewUser();
        }
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public boolean attemptLogin(String username, String password){
        return dataSource.attemptLogin(username, password);
    }

    public Result<LoggedInUser> login(String username, String password) {
        // handle login
        Result result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }

    public Result<LoggedInUser> register(String username, String password, String SQ, String SA) {
        // handle register
        Result result = dataSource.register(username, password, SQ, SA);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }

}
