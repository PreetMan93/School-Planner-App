package com.exam.planner.DSO;

import com.exam.planner.DSO.Events.Event;

import org.junit.Assert;
import org.junit.Test;

public class PlannerTest {

    @Test
    public void testAddRemoveEvent(){
        Planner p = new Planner("ID");
        Event e = new Event("123");
        p.addEvent(e);
        Assert.assertTrue("Add event to planner failed", p.eventListContains(e));
        p.removeEvent(e.getId());
        Assert.assertTrue("Remove event from planner failed", !p.eventListContains(e));
    }

    @Test
    public void testGet(){
        Planner p = new Planner("ID");
        Assert.assertTrue("get failed", p.getId().equals("ID"));
    }

}
