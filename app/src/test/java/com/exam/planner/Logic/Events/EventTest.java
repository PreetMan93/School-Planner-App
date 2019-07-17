package com.exam.planner.Logic.Events;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

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
    public void Getters(){
        assertTrue(testEvent.getStartYear()==testEvent.getStartDate().getYear());
        assertTrue(testEvent.getStartMonth()==testEvent.getStartDate().getMonth());
        assertTrue(testEvent.getStartDay()==testEvent.getStartDate().getDay());
        assertTrue(testEvent.getStartHour()==testEvent.getStartDate().getHour());
        assertTrue(testEvent.getStartMinute()==0);
        
        assertTrue(testEvent.getEndYear()==testEvent.getEndDate().getYear());
        assertTrue(testEvent.getEndMonth()==testEvent.getEndDate().getMonth());
        assertTrue(testEvent.getEndDay()==testEvent.getEndDate().getDay());
        assertTrue(testEvent.getEndHour()==testEvent.getStartHour()+1);
        assertTrue(testEvent.getEndMinute()==0);

    }
    @Test
    public void Event2(){
        Event newEvent = new Event("Comp3430");
        assertTrue(newEvent.getId().equals("Comp3430"));
    }

    @Test
    public void editName() {
        testEvent.editName("Testing");
        assertTrue(testEvent.getName().equals("Testing"));
    }
    @Test
    public void editStartDate()throws DateOutOfBoundsException {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        testEvent.editStartDate(tomorrow.get(Calendar.YEAR),tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DAY_OF_MONTH));
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
        assertTrue(testEvent.getStartDate().getDay()==tomorrow.get(Calendar.DAY_OF_MONTH) && testEvent.getStartDate().getMonth() == tomorrow.get(Calendar.MONTH));
    }

    @Test
    public void editStartDate2()throws DateOutOfBoundsException, TimeOutOfBoundsException {
        Calendar future = Calendar.getInstance();
        future.add(Calendar.DAY_OF_MONTH, 2);
        testEvent.editStartDate(future.get(Calendar.YEAR),future.get(Calendar.MONTH),future.get(Calendar.DAY_OF_MONTH),1,2);
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
        assertTrue(testEvent.getStartDate().getDay()==future.get(Calendar.DAY_OF_MONTH) && testEvent.getStartDate().getMonth() == future.get(Calendar.MONTH) && testEvent.getStartDate().getHour() == 1 && testEvent.getStartDate().getMinute() == 2);

    }

    @Test(expected = Exception.class)
    public void editEndDateFailure()throws DateOutOfBoundsException {
        testEvent.editEndDate(rightNow.getStartDate().getYear(),rightNow.getStartDate().getMonth(),32);
        assertTrue(rightNow.getEndDate().getDay()==testEvent.getEndDate().getDay());
    }

    @Test(expected = Exception.class)
    public void editEndDateFailure2()throws DateOutOfBoundsException, TimeOutOfBoundsException {
        testEvent.editEndDate(rightNow.getStartDate().getYear(),rightNow.getStartDate().getMonth(),rightNow.getStartDate().getDay(),0,74);
        assertTrue(rightNow.getEndDate().getDay()==testEvent.getEndDate().getDay());
    }

    @Test
    public void editEndDate()throws DateOutOfBoundsException,TimeOutOfBoundsException{
        testEvent.editStartDate(rightNow.getStartDate().getYear(),8,1,0,0);
        testEvent.editEndDate(rightNow.getStartDate().getYear(),8,2,0,0);
        assertTrue(rightNow.getEndDate().getMonth()!=testEvent.getEndDate().getMonth());

        assertTrue(testEvent.getEndDate().getDay()==testEvent.getStartDate().getDay()+1);
    }

    @Test
    public void editEndDate2()throws DateOutOfBoundsException, TimeOutOfBoundsException {
        testEvent.editEndDate(rightNow.getStartDate().getYear(),rightNow.getStartDate().getMonth(),rightNow.getStartDate().getDay(),rightNow.getStartDate().getHour(),42);
        assertTrue(rightNow.getEndDate().getDay()==testEvent.getEndDate().getDay());
        assertTrue(testEvent.getEndDate().getDay()==rightNow.getStartDate().getDay() && testEvent.getEndDate().getMinute()==42);
    }

    @Test
    public void editTime()throws TimeOutOfBoundsException{
        testEvent.getStartDate().editTime(1,1);
        assertTrue(testEvent.getStartHour()==1);
        assertTrue(testEvent.getStartMinute()==1);
    }

    @Test(expected = Exception.class)
    public void editTime2()throws TimeOutOfBoundsException{
        testEvent.getStartDate().editTime(24,1);
    }

    @Test(expected = Exception.class)
    public void editTime3()throws TimeOutOfBoundsException {
        testEvent.getStartDate().editTime(1, 60);
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
    public void eventCopy()throws DateOutOfBoundsException, TimeOutOfBoundsException {
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