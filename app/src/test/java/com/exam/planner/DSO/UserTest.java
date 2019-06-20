package com.exam.planner.DSO;

import com.exam.planner.DSO.Events.Event;

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
}
