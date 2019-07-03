package com.exam.planner.LoginTesting;

import com.exam.planner.Logic.Login.FormState;

import org.junit.Assert;
import org.junit.Test;

public class FormStateTest {

    @Test
    public void testConstructor1(){
        FormState formState1 = new FormState(10, 10);
        Assert.assertEquals(formState1.getUsernameError().intValue(), 10);
        Assert.assertEquals(formState1.getPasswordError().intValue(), 10);
    }

    @Test
    public void testConstructor2(){
        FormState formState2 = new FormState(true);
        Assert.assertTrue(formState2.isDataValid());
    }

    @Test
    public void testConstructor3(){
        FormState formState3 = new FormState(10, 10, true);
        Assert.assertEquals(formState3.getUsernameError().intValue(), 10);
        Assert.assertEquals(formState3.getPasswordError().intValue(), 10);
        Assert.assertTrue(formState3.isDataValid());
    }
}
