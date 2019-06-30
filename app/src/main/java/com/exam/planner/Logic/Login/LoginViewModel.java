package com.exam.planner.Logic.Login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Logic.Login.data.Result;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;
import com.exam.planner.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<FormState> FormState = new MutableLiveData<>();
    private Repository repository;
    private boolean newUser;

    public LoginViewModel(Repository repository) {
        this.repository = repository;
        newUser = true;
    }

    public LiveData<FormState> getFormState() {
        return FormState;
    }

    public boolean attemptLogin(String username, String password) {
        return repository.attemptLogin(username, password);
    }

    public void login(String username, String password) throws LoginFailureException{
        try {
            Result<LoggedInUser> result = repository.login(username, password);

            if (result instanceof Result.Success) {
                LoggedInUser newUserInfo = (LoggedInUser) ((Result.Success) result).getData();
                newUser = newUserInfo.isFirstLogin();
            } else {
                Log.e("Login error", "If this login error happens good fucking luck mate");
                throw new LoginFailureException("Failed to login not sure how that could happen");
                //TODO: Potentially try to force this error to occur somehow so it can be handled
            }
        } catch (RuntimeException e){
            throw new LoginFailureException("Failed to login not sure how that could happen");
        }
    }

    public void register(String username, String password, String SQ, String SA) throws RegisterFailureException{
        Result<LoggedInUser> result = repository.register(username, password, SQ, SA);

        if (result instanceof Result.Success) {
            LoggedInUser newUserInfo = (LoggedInUser)((Result.Success)result).getData();
            newUser = true;
        } else {
            Log.e("Register error", "If this register error happens good fucking luck mate");
            throw new RegisterFailureException("Failed to register not sure how that could happen");
            //TODO: Potentially try to force this error to occur somehow so it can be handled
        }
    }


    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            FormState.setValue(new FormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            FormState.setValue(new FormState(null, R.string.invalid_password));
        } else {
            FormState.setValue(new FormState(true));
        }
    }

    public void registerDataChanged(String username, String password1, String password2){
        if(!isUserNameValid(username)) {
            FormState.setValue(new FormState(R.string.invalid_username, null));
        } else if(!isPasswordValid(password1)) {
            FormState.setValue(new FormState(null, R.string.invalid_password));
        } else if(!isPasswordTheSame(password1, password2)){
            FormState.setValue(new FormState(null, R.string.passwords_dont_match));
        } else {
            FormState.setValue((new FormState(true)));
        }
    }

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        } else if(username.trim().length() < 3) {
            return false;
        } else if(invalidCharacters(username)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isPasswordValid(String password) {
        if (password == null){
            return false;
        } else if (password.trim().length() < 5){
            return false;
        } else if (invalidCharacters(password)){
            return false;
        } else{
            return true;
        }
    }

    private boolean isPasswordTheSame(String pass1, String pass2){
        return pass1.equals(pass2);
    }

    private boolean invalidCharacters(String string){
        String badChars = "!@#$%^&*()-_~?<>,.";
        for(int i = 0; i < string.length(); i++){
            if(badChars.contains("" + string.charAt(i)))
                return true;
        }
        return false;
    }

    public boolean isNewUser(){ return newUser; }

    public void notNewUser(){
        repository.notNew();
    }
}
