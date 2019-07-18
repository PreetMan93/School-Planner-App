package com.exam.planner.Integration;

import com.exam.planner.DSO.Planner;
import com.exam.planner.Logic.Events.DateTime;
import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;

import org.junit.Assert;
import org.junit.Test;

public class PlannerIntTest {

    Repository db = Repository.getInstance(UserPersistenceStub.getInstance());


    @Test
    public void testAddRemoveEvent(){
        db.login("username", "password");
        Planner p = db.getUser().getPlanner();
        Event e = new Event("123");

        Assert.assertTrue("Event 123 already exists in event list", !p.eventListContains(e));
        p.addEvent(e);
        Assert.assertTrue("Add event to planner failed", p.eventListContains(e));
        p.removeEvent(e.getId());
        Assert.assertTrue("Remove event from planner failed", !p.eventListContains(e));
        
    }

    @Test
    public void testGet(){
        db.login("username", "password");
        db.getUser().getPlanner().addEvent(new Event("ID"));
        Assert.assertEquals(db.getEvent("ID").get(0).getId(), "ID");
    }
    
    @Test
    public void testGetEventsByDayMonth(){
        db.login("username", "password");
        Planner p = db.getUser().getPlanner();
        int numCurrEvents = db.getEvents(new DateTime()).size();
        p.addEvent(new Event("ID"));
        p.addEvent(new Event("ID"));
        Assert.assertEquals(db.getEvents(new DateTime()).size(), 2 + numCurrEvents);
    }
}