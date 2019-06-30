package com.exam.planner.LoginTesting;

import com.exam.planner.Logic.Login.data.DataSource;
import com.exam.planner.Logic.Login.data.Result;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;

import org.junit.Assert;
import org.junit.Test;

public class DataSourceUnitTest {

    DataSource dataSource = new DataSource();

    @Test
    public void testAttemptLoginFailure(){
        Assert.assertTrue(!dataSource.attemptLogin("work", "work"));
    }

    @Test
    public void testAttemptLoginSuccess(){
        Assert.assertTrue(dataSource.attemptLogin("username", "password"));
    }

    @Test
    public void testRegisterFirstTimeUser(){
        Result result = dataSource.register("username1","password1", "", "");
        assert(result instanceof Result.Success);
        LoggedInUser data = (LoggedInUser)((Result.Success) result).getData();
        assert(data.isFirstLogin());
    }

    @Test
    public void testLoginNonFirstTimeUser(){
        Result result = dataSource.login("username","password");
        assert(result instanceof Result.Success);
        LoggedInUser data = (LoggedInUser)((Result.Success) result).getData();
        assert(!data.isFirstLogin());
    }
}
