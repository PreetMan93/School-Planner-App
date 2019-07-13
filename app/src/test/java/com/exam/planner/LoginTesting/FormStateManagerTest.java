package com.exam.planner.LoginTesting;

import com.exam.planner.Logic.Login.FormState;
import com.exam.planner.Logic.Login.FormStateManager;

import org.junit.Assert;
import org.junit.Test;

public class FormStateManagerTest {
    FormStateManager formStateManager = new FormStateManager("\"!@#$%^&*()-_~?<>,.\";\n");

    @Test
    public void testUsernameVerify(){
        Assert.assertTrue(formStateManager._testIsUsernameValid("user"));
        Assert.assertTrue(!formStateManager._testIsUsernameValid("!asd"));
        Assert.assertTrue(!formStateManager._testIsUsernameValid("us"));
        Assert.assertTrue(!formStateManager._testIsUsernameValid("te_st"));
        Assert.assertTrue(!formStateManager._testIsUsernameValid("____a"));
        Assert.assertTrue(!formStateManager._testIsUsernameValid("aaaaaa*"));
    }

    @Test
    public void testPasswordVerify(){
        Assert.assertTrue(formStateManager._testIsPasswordValid("12345"));
        Assert.assertTrue(formStateManager._testIsPasswordValid("password"));
        Assert.assertTrue(!formStateManager._testIsPasswordValid(""));
        Assert.assertTrue(!formStateManager._testIsPasswordValid("___"));
        Assert.assertTrue(!formStateManager._testIsPasswordValid("12345_"));
        Assert.assertTrue(formStateManager._testIsPasswordValid("111111111111"));
    }

    @Test
    public void testPasswordDuplicate(){
        Assert.assertTrue(formStateManager._testIsPasswordTheSame("swag", "swag"));
    }
}
