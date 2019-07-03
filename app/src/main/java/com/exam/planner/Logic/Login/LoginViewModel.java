package com.exam.planner.Logic.Login;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Logic.Login.data.Result;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;

public class LoginViewModel extends ViewModel {

    private Repository repository;
    private boolean newUser;

    public LoginViewModel(Repository repository) {
        this.repository = repository;
        newUser = true;
    }

    public boolean attemptLogin(String username, String password) {
        return repository.attemptLogin(username, password);
    }

    // A lot of redundant checking and validation
    public void login(String username, String password) throws LoginFailureException{
        try {
            Result<LoggedInUser> result = repository.login(username, password);

            if (result instanceof Result.Success) {
                newUser = false; // newUserInfo.isFirstLogin();
            } else {
                Log.e("Login error", "If this login error happens good fucking luck mate");
                throw new LoginFailureException("Failed to login not sure how that could happen");
            }
        } catch (RuntimeException e){
            throw new LoginFailureException("Failed to login not sure how that could happen");
        } catch (Exception e){
            throw e; // If this occurs I have no idea what could have gone wrong
        }
    }

    // A lot of redundant checking and validation
    public void register(String username, String password, String SQ, String SA) throws Exception{
        try {
            Result<LoggedInUser> result = repository.register(username, password, SQ, SA);

            if (result instanceof Result.Success) {
                newUser = true;
            } else if (result instanceof Result.Error) {
                Exception e = ((Result.Error) result).getError();
                if (e instanceof RegisterFailureException) {
                    throw e;
                } else {
                    Log.e("Register error", "If this registration error happens good fucking luck mate");
                    throw new Exception(e.getMessage());
                }
            }
        } catch (Exception e){
            throw e; // If this triggers I have no idea what has happened
        }
    }

    public boolean isNewUser(){ return newUser; }
    public void notNewUser(){
        repository.notNew();
    }
}
