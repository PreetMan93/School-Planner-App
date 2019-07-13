package com.exam.planner.Logic.Login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.exam.planner.R;

public class FormStateManager {

    private MutableLiveData<FormState> FormState = new MutableLiveData<>();
    private String badChars;

    public FormStateManager(String badChars){
        this.badChars = badChars;
    }

    public LiveData<FormState> getFormState() {
        return FormState;
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
        for(int i = 0; i < string.length(); i++){
            if(badChars.contains("" + string.charAt(i)))
                return true;
        }
        return false;
    }

    public boolean _testIsUsernameValid(String username){ return isUserNameValid(username); }
    public boolean _testIsPasswordValid(String password){ return isUserNameValid(password); }
    public boolean _testIsPasswordTheSame(String pass1, String pass2){ return isPasswordTheSame(pass1, pass2);}
}
