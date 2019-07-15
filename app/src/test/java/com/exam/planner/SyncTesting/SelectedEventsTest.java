package com.exam.planner.SyncTesting;

import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Sync.SelectedEvents;

import org.junit.Assert;
import org.junit.Test;

public class SelectedEventsTest {

    @Test
    public void testClass(){
        SelectedEvents selectedEvents = new SelectedEvents();
        Event e = new Event("ID");
        selectedEvents.add(e);
        Assert.assertTrue(selectedEvents.contains("ID"));
        selectedEvents.remove(e);
        Assert.assertFalse(selectedEvents.contains("ID"));
    }

}
