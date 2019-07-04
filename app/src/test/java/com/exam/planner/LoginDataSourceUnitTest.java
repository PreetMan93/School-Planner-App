package com.exam.planner;

import com.exam.planner.Logic.Login.data.LoginDataSource;
import com.exam.planner.Logic.Login.data.Result;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;

import org.junit.Test;

public class LoginDataSourceUnitTest {

    LoginDataSource dataSource = new LoginDataSource();

    @Test
    public void testFirstTimeUser(){
        Result result = dataSource.login("wrong","wrong");
        assert(result instanceof Result.Success);
        LoggedInUser data = (LoggedInUser)((Result.Success) result).getData();
        assert(data.isFirstLogin());
    }

    @Test
    public void testNonFirstTimeUser(){
        Result result = dataSource.login("username","password");
        assert(result instanceof Result.Success);
        LoggedInUser data = (LoggedInUser)((Result.Success) result).getData();
        assert(!data.isFirstLogin());
    }
}
