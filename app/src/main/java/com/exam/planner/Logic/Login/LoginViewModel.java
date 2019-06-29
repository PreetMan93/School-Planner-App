package com.exam.planner.Logic.Login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.exam.planner.Logic.Login.data.LoginRepository;
import com.exam.planner.Logic.Login.data.Result;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;
import com.exam.planner.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<FormState> FormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private boolean newUser;

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
        newUser = true;
    }

    public LiveData<FormState> getFormState() {
        return FormState;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
//        boolean newUser = false;
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
            LoggedInUser newUserInfo = (LoggedInUser)((Result.Success)result).getData();
            newUser = newUserInfo.isFirstLogin();
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
//        return newUser;
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
            return !username.trim().isEmpty();
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
        String badChars = "!@#$%^&*()-\\\'~?<>,.";
        for(String s : string.split("")){
            if(badChars.contains(s))
                return true;
        }
        return false;
    }

    public boolean isNewUser(){ return newUser; }

    public void notNewUser(){
        loginRepository.notNew();
    }
}
