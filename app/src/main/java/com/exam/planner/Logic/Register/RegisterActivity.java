package com.exam.planner.Logic.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.planner.Logic.CalendarPage.CalendarActivity;
import com.exam.planner.Logic.Utility.PrefManager;
import com.exam.planner.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, confirmPasswordEditText, secretQuestionEditText,
                     secretAnswerEditText;
    private CheckBox checkBox;
    private String username, password, confirmPassword, secretQuestion, secretAnswer;

    private PrefManager prefManager;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Intent intent = new Intent(this, CalendarActivity.class);

        usernameEditText = findViewById(R.id.registerEmail);
        passwordEditText = findViewById(R.id.registerPassword);
        confirmPasswordEditText = findViewById(R.id.registerConfirmPassword);
        secretAnswerEditText = findViewById(R.id.registerSecretAnswer);
        secretQuestionEditText = findViewById(R.id.registerSecretQuestion);
        registerButton = findViewById(R.id.registerButton);
        checkBox = findViewById(R.id.saveInfo);

        prefManager = new PrefManager(this);
        prefManager.checkSharedPreferences(usernameEditText, passwordEditText, checkBox);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setPreferences(checkBox.isChecked(), usernameEditText, passwordEditText);
                register();
                startActivity(intent);
            }
        });
    }

    public void register() {
        setStrings();

        if(validateText()) {
            success();
        }
        else {
            Toast.makeText(this,"Signup failure",Toast.LENGTH_LONG).show();
        }
    }

    public void setStrings() {

        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        confirmPassword = confirmPasswordEditText.getText().toString().trim();
        secretQuestion = secretQuestionEditText.getText().toString().trim();
        secretAnswer = secretAnswerEditText.getText().toString().trim();
    }

    public boolean validateText() {
        boolean isValid = true;

        if(password.isEmpty() || password.length() <=5) {
            passwordEditText.setError("Please enter a valid password with more than 5 letters / numbers");
            isValid = false;
        }
        if(confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            confirmPasswordEditText.setError("Your passwords don't match");
            isValid = false;
        }
        if(username.isEmpty() || username.length() < 3) {
            usernameEditText.setError("User name must be at least 3 characters");
            isValid = false;
        }

        return isValid;
    }

    public void success() {
        //TODO save credentials to persistence layer
        Toast.makeText(this,"Success!",Toast.LENGTH_LONG).show();
    }
}

















