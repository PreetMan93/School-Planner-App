package com.exam.planner.Presentation.LoginAndRegister;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.planner.Logic.Login.FormState;
import com.exam.planner.Logic.Login.LoginViewModel;
import com.exam.planner.Logic.Login.LoginViewModelFactory;
import com.exam.planner.Logic.Login.RegisterFailureException;
import com.exam.planner.Presentation.CalendarPage.CalendarActivity;
import com.exam.planner.R;

public class RegisterActivity extends AppCompatActivity {


    /* Todo This is terrible. Combine register into login meaning utilize the features already
       created in login
     */

    private LoginViewModel loginViewModel;

    private EditText usernameEditText, passwordEditText, confirmPasswordEditText, secretQuestionEditText,
                     secretAnswerEditText;
    private CheckBox checkBox;

    private PrefManager prefManager;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Intent intent = new Intent(this, CalendarActivity.class);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);


        usernameEditText = findViewById(R.id.registerEmail);
        passwordEditText = findViewById(R.id.registerPassword);
        confirmPasswordEditText = findViewById(R.id.registerConfirmPassword);
        secretAnswerEditText = findViewById(R.id.registerSecretAnswer);
        secretQuestionEditText = findViewById(R.id.registerSecretQuestion);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setEnabled(false);
        checkBox = findViewById(R.id.saveInfo);

        prefManager = new PrefManager(this);
        prefManager.checkSharedPreferences(usernameEditText, passwordEditText, checkBox);


        loginViewModel.getFormState().observe(this, new Observer<FormState>() {
            @Override
            public void onChanged(@Nullable FormState formState) {
                if (formState == null) {
                    return;
                }
                registerButton.setEnabled(formState.isDataValid());
                if (formState.getUsernameError() != null) {
                    usernameEditText.setError(getString(formState.getUsernameError()));
                }
                if (formState.getPasswordError() != null) {
                    confirmPasswordEditText.setError(getString(formState.getPasswordError()));
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
                loginViewModel.registerDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), confirmPasswordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        confirmPasswordEditText.addTextChangedListener(afterTextChangedListener);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setPreferences(checkBox.isChecked(), usernameEditText, passwordEditText);
                try {
                    loginViewModel.register(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(), secretQuestionEditText.getText().toString(),
                            secretAnswerEditText.getText().toString());
                } catch (RegisterFailureException e) {
                    Toast.makeText(getApplicationContext(),"Register failure reload and try again", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Fatal Error", Toast.LENGTH_LONG).show();
                }
                toast();
                startActivity(intent);
            }
        });
    }

    public void toast() {
        Toast.makeText(this,"Welcome!",Toast.LENGTH_LONG).show();
    }
}

















