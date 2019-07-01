package com.exam.planner.DSO.Events;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class EventTest {
    Event testEvent;
    Event rightNow;
    @Before public void setUp(){
        testEvent = new Event();
        rightNow = new Event();
    }

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
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
        assertTrue(testEvent.getStartDate().getDay()==17 && testEvent.getStartDate().getMonth() == 7);
    }

    @Test
    public void editStartDate1() {
        testEvent.editStartDate(2019,7,18,1,2);
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
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
        testEvent.editStartDate(rightNow.getStartDate().getYear(),8,1);
        testEvent.getStartDate().printDate();
        System.out.println("this worked");
        testEvent.editEndDate(rightNow.getStartDate().getYear(),8,2);
        assertTrue(rightNow.getEndDate().getMonth()!=testEvent.getEndDate().getMonth());
        assertTrue(testEvent.getEndDate().getDay()==testEvent.getStartDate().getDay()+1);
    }

    @Test
    public void editEndDate1() {
        testEvent.editEndDate(rightNow.getStartDate().getYear(),rightNow.getStartDate().getMonth(),rightNow.getStartDate().getDay(),rightNow.getStartDate().getHour(),42);
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