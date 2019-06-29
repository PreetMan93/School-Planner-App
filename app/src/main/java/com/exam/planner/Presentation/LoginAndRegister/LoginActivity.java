package com.exam.planner.Presentation.LoginAndRegister;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.exam.planner.Logic.Login.FormState;
import com.exam.planner.Logic.Login.LoginViewModel;
import com.exam.planner.Logic.Login.LoginViewModelFactory;
import com.exam.planner.Presentation.CalendarPage.CalendarActivity;
import com.exam.planner.Logic.Utility.PrefManager;
import com.exam.planner.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private PrefManager prefManager;

    private EditText usernameEditText, passwordEditText;
    private CheckBox rememberInfoCheckBox;
    private Button loginButton;

    AlertDialog.Builder builder;


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

        prefManager = new PrefManager(this);
        builder = new AlertDialog.Builder(this);

        prefManager.checkSharedPreferences(usernameEditText, passwordEditText, rememberInfoCheckBox);
        tryEnableButton();

        // Set up dialog box
        builder.setTitle(R.string.app_name);
        builder.setMessage("Are you sure you'd like to create a new account?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                prefManager.setPreferences(true, usernameEditText, passwordEditText);
                register();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });


        loginViewModel.getFormState().observe(this, new Observer<FormState>() {
            @Override
            public void onChanged(@Nullable FormState formState) {
                if (formState == null) {
                    return;
                }
                loginButton.setEnabled(formState.isDataValid());
                if (formState.getUsernameError() != null) {
                    usernameEditText.setError(getString(formState.getUsernameError()));
                }
                if (formState.getPasswordError() != null) {
                    passwordEditText.setError(getString(formState.getPasswordError()));
                }
            }
        });


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
                    prefManager.setPreferences(rememberInfoCheckBox.isChecked(), usernameEditText, passwordEditText);
                    tryEnableButton();
                    updateUiWithUser();
                    finish();
                }

            }
        });
    }

    private void tryEnableButton(){
        if(rememberInfoCheckBox.isEnabled()){
            loginButton.setEnabled(true);
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

    private void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
