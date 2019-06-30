package com.exam.planner.LoginTesting;

import com.exam.planner.Logic.Login.FormState;

import org.junit.Assert;
import org.junit.Test;

public class FormStateTest {

    @Test
    public void testGets(){
        FormState formState = new FormState(10, 10, true);
        Assert.assertEquals(formState.getUsernameError().intValue(), 10);
        Assert.assertEquals(formState.getPasswordError().intValue(), 10);
        Assert.assertTrue(formState.isDataValid());
    }
}
