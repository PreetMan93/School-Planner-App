package com.exam.planner.DSO;

import com.exam.planner.Logic.Events.Event;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    public void testAddRepeatEvent(){
        Planner p = new Planner("ID");
        boolean[] repArray = {true, true, true, true, true, true, true};
        p.editEvent("-1", "Test", 2000, 1, 1, 0, 0, 2000, 1, 1, 1, 0, 2000, 1, 7, repArray);
        Assert.assertEquals(p.getEventsList().size(), 7);
    }

    @Test
    public void mockRepeaterEvent(){
        Planner p = mock(Planner.class);
        boolean[] repArray = {true, true, true, true, true, true, true};
        p.editEvent("-1", "Test", 2000, 1, 1, 0, 0, 2000, 1, 1, 1, 0, 2000, 1, 7, repArray);
        verify(p, times(1)).editEvent("-1", "Test", 2000, 1, 1, 0, 0, 2000, 1, 1, 1, 0, 2000, 1, 7, repArray);
    }

    @Test
    public void testGet(){
        Planner p = new Planner("ID");
        Assert.assertTrue("get failed", p.getId().equals("ID"));
    }

    @Test
    public void testGetEvent(){
        Planner p = new Planner("ID");
        p.addEvent(new Event("Test"));

        Assert.assertNotNull(p.getEvent("Test"));
        Assert.assertNull(p.getEvent("Test2"));
    }

    @Test
    public void testGetEventDate(){
        Planner p = new Planner("ID");
        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);

        p.addEvent(new Event());
        Assert.assertEquals(p.getEventsList(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)).size(), 1);
        Assert.assertEquals(p.getEventsList(tomorrow.get(Calendar.YEAR), tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DAY_OF_MONTH)).size(), 0);
    }

    @Test
    public void testRemoveCopies() {
        Planner p = new Planner("ID");
        Event e = new Event();
        Event e2 = new Event();
        e.editCopyId("test");
        e2.editCopyId("test");

        p.addEvent(e);
        p.addEvent(e2);

        Assert.assertTrue(p.removeEventCopies("test"));
        Assert.assertFalse(p.eventListContains(e));
        Assert.assertFalse(p.eventListContains(e2));
    }
}
