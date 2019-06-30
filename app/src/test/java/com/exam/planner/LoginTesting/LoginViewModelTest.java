package com.exam.planner.LoginTesting;

import com.exam.planner.Logic.Login.LoginFailureException;
import com.exam.planner.Logic.Login.LoginViewModel;
import com.exam.planner.Logic.Login.RegisterFailureException;
import com.exam.planner.Logic.Login.data.DataSource;
import com.exam.planner.Logic.Login.data.Repository;

import org.junit.Assert;
import org.junit.Test;

public class LoginViewModelTest {

    private LoginViewModel loginViewModel = new LoginViewModel(Repository.getInstance(new DataSource()));


    @Test
    public void testNewUser() {
        Assert.assertTrue(loginViewModel.isNewUser());
        loginViewModel.notNewUser();
        Assert.assertTrue(loginViewModel.isNewUser());
    }

    @Test
    public void testAttemptLoginSuccess() {
        Assert.assertTrue(loginViewModel.attemptLogin("username", "password"));
    }

    @Test
    public void testAttemptLoginFailure() {
        Assert.assertTrue(!loginViewModel.attemptLogin("failure", "failure"));
    }

    @Test
    public void testLoginFailure() {
        try {
            loginViewModel.login("failure", "failure");
            Assert.fail("login was successful when it shouldn't have been");
        } catch (LoginFailureException e) {
            Assert.assertTrue("Didn't catch an error when it should have", true);
        } catch (Exception e) {
            Assert.fail("Wrong error caught " + e.getClass());
        }
    }

    @Test
    public void testLoginSuccess() {
        try {
            loginViewModel.login("username", "password");
            Assert.assertTrue("login was successful when it shouldn't have been", true);
        } catch (LoginFailureException e) {
            Assert.fail("Didn't catch an error when it should have");
        } catch (Exception e) {
            Assert.fail("Wrong error caught " + e.getClass());
        }
    }

    @Test
    public void testRegisterSuccess() {
        try {
            loginViewModel.register("username1", "password1", "", "");
            Assert.assertTrue("Register wasn't successful when it should have been", true);
        } catch (RegisterFailureException e) {
            Assert.fail("Didn't catch an error when it should have");
        } catch (Exception e) {
            Assert.fail("Wrong error caught " + e.getClass());
        }
    }

}
