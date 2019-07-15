package com.exam.planner.LoginTesting;

import com.exam.planner.Logic.Events.DateTime;
import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Logic.Login.data.Result;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;

import org.junit.Assert;
import org.junit.Test;

public class RepositoryTest {

    Repository test = Repository.getInstance(UserPersistenceStub.getInstance());

    @Test
    public void testAttemptLoginSuccess(){
        Assert.assertTrue(test.attemptLogin("username", "password"));
    }

    @Test
    public void testAttemptLoginFailure(){
        Assert.assertTrue(!test.attemptLogin("user", "failure"));
    }

    @Test
    public void testLoginSuccess(){
        Result res = test.login("username", "password");
        Assert.assertTrue(res instanceof Result.Success);
    }

    @Test
    public void testLoginFailure(){
        Result res = test.login("user", "failure");
        Assert.assertTrue(res instanceof Result.Error);
    }

    @Test
    public void testRegisterSuccess(){
        Result res = test.register("username1", "password1", "SQ", "SA");
        Assert.assertTrue(res instanceof Result.Success);
    }

    @Test
    public void testGetEventById(){
        test.login("username", "password");
        test.getUser().getPlanner().addEvent(new Event("ID"));
        Assert.assertEquals(test.getEvent("ID").get(0).getId(), "ID");
    }

    @Test
    public void testGetEventsByDayMonth(){
        test.login("username", "password");
        int numCurrEvents = test.getEvents(new DateTime()).size();
        test.getUser().getPlanner().addEvent(new Event("ID"));
        test.getUser().getPlanner().addEvent(new Event("ID"));
        Assert.assertEquals(test.getEvents(new DateTime()).size(), 2 + numCurrEvents);
    }
}
