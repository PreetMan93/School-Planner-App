package com.exam.planner.DSO;

import com.exam.planner.Logic.Events.Event;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {


    @Test
    public void testAddEvent(){
        User u = new User("ID", "user", "pass");
        Event e = new Event("123");
        u.addEvent(e);
        Assert.assertTrue("failed to add", u.isDuplicate(e));
    }

    @Test
    public void testRemoveEvent(){
        User u = new User("ID", "user", "pass");
        Event e = new Event("123");
        u.addEvent(e);
        Assert.assertTrue("Failed to remove", u.removeEvent("123"));
        Assert.assertTrue("Removed non existent item", !u.removeEvent("123"));
    }

    @Test
    public void testGets(){
        User u = new User("ID", "user", "pass", "question", "answer");
        Assert.assertEquals(u.getId(), "ID");
        Assert.assertEquals(u.getUsername(), "user");
        Assert.assertEquals(u.getPassword(), "pass");
        Assert.assertEquals(u.getSQ(), "question");
        Assert.assertEquals(u.getSA(), "answer");
    }

    @Test
    public void testToString(){
        User u = new User("ID", "user", "pass");
        Assert.assertEquals(u.toString(), "ID: ID Username: user");
    }
}
