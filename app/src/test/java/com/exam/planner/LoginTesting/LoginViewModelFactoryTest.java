package com.exam.planner.LoginTesting;

import android.arch.lifecycle.ViewModel;

import com.exam.planner.Logic.Login.LoginViewModel;
import com.exam.planner.Logic.Login.LoginViewModelFactory;
import com.exam.planner.Logic.Login.data.DataSource;
import com.exam.planner.Logic.Login.data.Repository;

import org.junit.Assert;
import org.junit.Test;


public class LoginViewModelFactoryTest {

    LoginViewModelFactory viewModelFactoryTest = new LoginViewModelFactory();

    @Test
    public void testCreateSuccess(){
        LoginViewModel loginViewModel = viewModelFactoryTest.create(LoginViewModel.class);
        Assert.assertTrue("Failed instance of LoginViewModel", loginViewModel instanceof LoginViewModel);
    }

    @Test
    public void testCreateFailure(){
        try {
            viewModelFactoryTest.create(TestingViewModel.class);
            Assert.assertTrue("Didn't catch error when they should have", false);
        } catch (IllegalArgumentException e){
            Assert.assertTrue (true);
        } catch (Exception e) {
            Assert.assertTrue("Caught incorrect error", false);
        }
    }
}

class TestingViewModel extends LoginViewModel{
    public TestingViewModel(){
        super(Repository.getInstance(new DataSource()));
    }
}