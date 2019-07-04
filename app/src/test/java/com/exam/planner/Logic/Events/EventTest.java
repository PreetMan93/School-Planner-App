package com.exam.planner.Logic.Events;

import org.junit.Before;
import org.junit.Test;

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
        String test;
        testEvent.printEvent();
        test = testEvent.getStartDateString();
        System.out.print(test);
        test = testEvent.getStartTimeString();
        System.out.print(test);
        test = testEvent.getEndDateString();
        System.out.print(test);
        test = testEvent.getEndTimeString();
        System.out.print(test);
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
        testEvent.editStartDate(2019,7,17);
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
        assertTrue(testEvent.getStartDate().getDay()==17 && testEvent.getStartDate().getMonth() == 7);
    }

    @Test
    public void editStartDate2()throws DateOutOfBoundsException, TimeOutOfBoundsException {
        testEvent.editStartDate(2019,7,18,1,2);
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
        assertTrue(testEvent.getStartDate().getDay()==18 && testEvent.getStartDate().getMonth() == 7 && testEvent.getStartDate().getHour() == 1 && testEvent.getStartDate().getMinute() == 2);

    }
    @Test
    public void editStartDate3()throws DateOutOfBoundsException, TimeOutOfBoundsException {
        testEvent.editStartDate("2019/7/18","1:2");
        assertTrue(rightNow.getStartDate().getDay()!=testEvent.getStartDate().getDay());
        assertTrue(testEvent.getStartDate().getDay()==18 && testEvent.getStartDate().getMonth() == 7 && testEvent.getStartDate().getHour() == 1 && testEvent.getStartDate().getMinute() == 2);
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
    public void editEndDate3()throws DateOutOfBoundsException, TimeOutOfBoundsException {
        testEvent.editEndDate("2019/7/18","1:2");
        assertTrue(rightNow.getEndDate().getDay()!=testEvent.getEndDate().getDay());
        assertTrue(testEvent.getEndDate().getDay()==18 && testEvent.getEndDate().getMonth() == 7 && testEvent.getEndDate().getHour() == 1 && testEvent.getEndDate().getMinute() == 2);
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
    public void isEndDatePriorToStart(){
        assertTrue(!testEvent.endDatePriorToStart());
    }

    @Test
    public void isEndDatePriorToStart2() throws DateOutOfBoundsException {
        testEvent.editEndDate(2018,10,12);
        assertTrue(testEvent.endDatePriorToStart());
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

    @Test
    public void validateDate() throws EventDateInvalidFormatException, DateOutOfBoundsException {
        testEvent.validateDate("2019/10/10");
    }
    @Test(expected = Exception.class)
    public void validateDateFailure() throws EventDateInvalidFormatException, DateOutOfBoundsException {
        testEvent.validateDate("2019");
    }
    @Test(expected = Exception.class)
    public void validateDateFailure2() throws EventDateInvalidFormatException, DateOutOfBoundsException {
        testEvent.validateDate("1800/10/10");
    }
    @Test(expected = Exception.class)
    public void validateDateFailure3() throws EventDateInvalidFormatException, DateOutOfBoundsException {
        testEvent.validateDate("2019/13/10");
    }
    @Test(expected = Exception.class)
    public void validateDateFailure4() throws EventDateInvalidFormatException, DateOutOfBoundsException {
        testEvent.validateDate("2019/10/34");
    }


    @Test
    public void validateTime() throws EventTimeInvalidFormatException, TimeOutOfBoundsException {
        testEvent.validateTime("23:59");
    }
    @Test(expected = Exception.class)
    public void validateTime2() throws EventTimeInvalidFormatException, TimeOutOfBoundsException {
        testEvent.validateTime("23");
    }
    @Test(expected = Exception.class)
    public void validateTime3() throws EventTimeInvalidFormatException, TimeOutOfBoundsException {
        testEvent.validateTime("25:59");
    }
    @Test(expected = Exception.class)
    public void validateTime4() throws EventTimeInvalidFormatException, TimeOutOfBoundsException {
        testEvent.validateTime("23:72");
    }
}