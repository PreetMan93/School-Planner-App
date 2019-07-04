package com.exam.planner.LoginTesting;

import com.exam.planner.Logic.Login.data.Result;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;

import org.junit.Assert;
import org.junit.Test;

public class ResultTest {

    @Test
    public void testError(){
        Result result = new Result.Error(new Exception("Testing"));
        Assert.assertEquals(""+((Result.Error) result).getError(), "java.lang.Exception: Testing");
        Assert.assertEquals(result.toString(), "Error[exception=java.lang.Exception: Testing]");
    }

    @Test
    public void testSuccess(){
        Result result = new Result.Success(new LoggedInUser("name", "ID",true, null));
        Assert.assertEquals(((LoggedInUser)((Result.Success) result).getData()).getDisplayName(), "name");
        Assert.assertTrue((result.toString()).contains("Success"));
    }
}
