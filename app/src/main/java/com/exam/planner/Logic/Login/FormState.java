package com.exam.planner.Logic.Login;

import android.support.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
public class FormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    private boolean isDataValid;

    public FormState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    public FormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    public FormState(@Nullable Integer usernameError, @Nullable Integer passwordError, boolean valid){
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = valid;
    }

    @Nullable
    public Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
