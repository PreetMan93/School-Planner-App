package com.exam.planner.Logic.login;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.planner.Logic.CalendarPage.CalendarActivity;
import com.exam.planner.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    public static final String MyPrefs = "preference";
    public static final String Username = "username";
    public static final String Password = "password";
    public static final String Checkbox = "remember";

    private EditText usernameEditText, passwordEditText;
    private CheckBox rememberInfoCheckBox;
    private Button loginButton;

    AlertDialog.Builder builder;
    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        rememberInfoCheckBox = findViewById(R.id.saveInfo);
        loginButton = findViewById(R.id.login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        checkSharedPreferences();
        builder = new AlertDialog.Builder(this);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        // Set up dialog box
        builder.setTitle(R.string.app_name);
        builder.setMessage("Are you sure you'd like to create a new account?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                setPreferences();
                finish();
                updateUiWithUser();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        /*loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {

                }
                setResult(Activity.RESULT_OK);
            }
        });*/


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(usernameEditText.getText().toString(),
                                passwordEditText.getText().toString());
                boolean firstTimeUser = loginViewModel.isNewUser();
                if (firstTimeUser){
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{
                    loginViewModel.notNewUser();
                    setPreferences();
                    updateUiWithUser();
                    finish();
                }

            }
        });
    }

    private void setPreferences(){
        // Save preferences on successful login
        prefEditor = pref.edit();
        if(rememberInfoCheckBox.isChecked()) {
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

    private void checkSharedPreferences(){

        String chkbox = pref.getString(Checkbox, "False");
        String user = pref.getString(Username, "");
        String pass = pref.getString(Password, "");

        usernameEditText.setText(user);
        passwordEditText.setText(pass);
        if (chkbox.equals("True")){
            rememberInfoCheckBox.setChecked(true);
            loginButton.setEnabled(true);
        }
        else{
            rememberInfoCheckBox.setChecked(false);
        }
    }

    private void updateUiWithUser() {
        String welcome = "";
        if (loginViewModel.isNewUser()) {
            welcome += "Welcome to the app!";
        }
        else{
            welcome += "Welcome back";
        }
        // TODO : initiate successful logged in experience
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
