package com.exam.planner.Logic.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.EditText;

public class PrefManager {

    private static final String Username = "username";
    private static final String Password = "password";
    private static final String Checkbox = "remember";

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    public PrefManager(Context context){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = pref.edit();
    }

    public void setPreferences(boolean checked, EditText usernameEditText, EditText passwordEditText){
        // Save preferences on successful login
        if(checked) {
            // Store user info
            prefEditor.putString(Username, usernameEditText.getText().toString());
            prefEditor.commit();

            prefEditor.putString(Password, passwordEditText.getText().toString());
            prefEditor.commit();

            prefEditor.putString(Checkbox, "True");
            prefEditor.commit();
        }
        else{
            // Clear stored user data
            prefEditor.putString(Username, "");
            prefEditor.commit();

            prefEditor.putString(Password, "");
            prefEditor.commit();

            prefEditor.putString(Checkbox, "False");
            prefEditor.commit();
        }
    }

    public void checkSharedPreferences(EditText usernameEditText, EditText passwordEditText, CheckBox checkBox){

        String chkbox = pref.getString(Checkbox, "False");
        String user = pref.getString(Username, "");
        String pass = pref.getString(Password, "");

        usernameEditText.setText(user);
        passwordEditText.setText(pass);
        if (chkbox.equals("True")){
            checkBox.setChecked(true);
        }
        else{
            checkBox.setChecked(false);
        }
    }
}
