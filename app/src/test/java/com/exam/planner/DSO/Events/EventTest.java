package com.exam.planner.DSO.Events;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {

    Event testEvent = new Event();
    Event rightNow = new Event();
    @Test
    public void Event(){
        testEvent.printEvent();
    }

    @Test
    public void editName() {
        testEvent.editName("Testing");
        assertTrue(testEvent.getName().equals("Testing"));
    }

    @Test
    public void editStartDateFailure() {
        testEvent.editStartDate(2019,5,12);
        assertTrue(rightNow.getStartDate().getDay()==testEvent.getStartDate().getDay());
    }

    @Test
    public void editStartDateFailure1() {
        testEvent.editStartDate(2019,5,12, 1, 1);
        assertTrue(rightNow.getStartDate().getDay()==testEvent.getStartDate().getDay());
    }

    @Test
    public void editStartDate() {
        testEvent.editStartDate(2019,7,17);
        // Todo Fix this test Evan
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
        assertTrue(testEvent.getStartDate().getDay()==17 && testEvent.getStartDate().getMonth() == 7);
    }

    @Test
    public void editStartDate1() {
        testEvent.editStartDate(2019,7,18,1,2);
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
        System.out.println(testEvent.getStartDate().getHour());
        assertTrue(testEvent.getStartDate().getDay()==18 && testEvent.getStartDate().getMonth() == 7 && testEvent.getStartDate().getHour() == 1 && testEvent.getStartDate().getMinute() == 2);

    }

    @Test
    public void editEndDateFailure() {
        testEvent.editEndDate(rightNow.getStartDate().getYear(),rightNow.getStartDate().getMonth(),rightNow.getStartDate().getDay());
        assertTrue(rightNow.getEndDate().getDay()==testEvent.getEndDate().getDay());
    }

    @Test
    public void editEndDateFailure1() {
        testEvent.editEndDate(rightNow.getStartDate().getYear(),rightNow.getStartDate().getMonth(),rightNow.getStartDate().getDay(),0,0);
        assertTrue(rightNow.getEndDate().getDay()==testEvent.getEndDate().getDay());
    }

    @Test
    public void editEndDate() {
        testEvent.editEndDate(rightNow.getStartDate().getYear(),rightNow.getStartDate().getMonth(),rightNow.getStartDate().getDay()+1);
        assertTrue(rightNow.getEndDate().getDay()!=testEvent.getEndDate().getDay());
        assertTrue(testEvent.getEndDate().getDay()==rightNow.getStartDate().getDay()+1);
    }

    @Test
    public void editEndDate1() {
        testEvent.editEndDate(rightNow.getStartDate().getYear(),rightNow.getStartDate().getMonth(),rightNow.getStartDate().getDay(),0,42);
        assertTrue(rightNow.getEndDate().getDay()==testEvent.getEndDate().getDay());
        assertTrue(testEvent.getEndDate().getDay()==rightNow.getStartDate().getDay() && testEvent.getEndDate().getMinute()==42);
    }

    @Test
    public void editId() {
        testEvent.editId("Comp3350");
        assertTrue(testEvent.getId().equals("Comp3350"));
    }

    @Test
    public void editColour() {
        testEvent.editColour("Blue");
        assertTrue(testEvent.getColour().equals("Blue"));
    }

    @Test
    public void makeEventPublic() {
        testEvent.makeEventPublic();
        assertTrue(testEvent.isPublic());
    }

    @Test
    public void makeEventPrivate() {
        testEvent.makeEventPrivate();
        assertTrue(!testEvent.isPublic());
    }

    @Test
    public void eventCopy() {
        testEvent.editName("Testing");
        Event copiedEvent = new Event();
        assertTrue(copiedEvent != testEvent);
        testEvent.eventCopy(copiedEvent,2019, 7,20);
        assertTrue(copiedEvent != testEvent);
        assertTrue(copiedEvent.getName() == testEvent.getName());
        assertTrue(copiedEvent.getStartDate().getDay() == 20);

    }

    @Test
    public void testCompareEvent(){
        Event a = new Event();
        a.editId("123");
        Event b = new Event();
        b.editId("123");
        assertTrue(a.compare(b));
        b.editId("321");
        assertTrue(!a.compare(b));
    }
}