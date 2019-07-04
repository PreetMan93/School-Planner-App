package com.exam.planner.Logic.Events;

import android.util.Log;

import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class Event
{
    private static final String TAG = "Event";
    private String name;
    private DateTime startDate, endDate;
    private String id;
    private String colour;
    private Boolean isPublic = false;

    public Event(){
        this.name = null;
        this.startDate = new DateTime();
        this.endDate = new DateTime(startDate, 1, 0);
        this.id = null;
        this.colour = "grey";
    }
    public Event(String id){
        this.name = null;
        this.startDate = new DateTime();
        this.endDate = new DateTime(startDate, 1, 0);
        this.id = id;
        this.colour = "grey";
    }


    public String getName() {return this.name;}
    public DateTime getStartDate() {return this.startDate;}
    public DateTime getEndDate() {return this.endDate;}
    public String getId() {return this.id;}
    public String getColour() {return this.colour;}
    public Boolean isPublic() {return this.isPublic;}

    public boolean compare(Event e){
        return (this.id.equals(e.id));
    }

    public void printEvent(){
        System.out.println("The Event has these values:");
        System.out.println("Name: "+this.name);
        System.out.print("Start time: ");
        this.startDate.printDate();
        System.out.print("End time: ");
        this.endDate.printDate();
        System.out.println("Id: "+this.id);
        System.out.println("Colour: "+this.colour);
        System.out.println("isPublic? "+this.isPublic);
    }
    public static void validateDate(String date) throws EventDateInvalidFormatException, DateOutOfBoundsException{
        String[] split = date.split("/");
        if (split.length != 3)
            throw(new EventDateInvalidFormatException("Date must follow the format YYYY/MM/DD"));
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        DateTime test = new DateTime();
        test.editDate(year,month,day);
    }

    public static void validateTime(String time) throws EventTimeInvalidFormatException, TimeOutOfBoundsException {
        String[] split = time.split(":");
        if (split.length != 2)
            throw(new EventTimeInvalidFormatException("Time must follow the format HH:MM"));
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        DateTime test = new DateTime();
        test.editTime(hour,minute);
    }

    public boolean endDatePriorToStart(){
        if (this.getEndDate().getDate().before(this.getStartDate().getDate()))
            return true;
        else
            return false;
    }

    public String getStartDateString(){return this.getStartDate().getDate().get(YEAR) + "/" + this.getStartDate().getDate().get(MONTH) + "/" + this.getStartDate().getDate().get(DAY_OF_MONTH);}

    public String getStartTimeString(){
        String hour = Integer.toString(this.getStartDate().getHour());
        String minute = Integer.toString(this.getStartDate().getMinute());
        if (minute.length() == 1)
            minute = "0" + minute;
        return hour + ":" + minute;
    }

    public String getEndDateString(){return this.getEndDate().getDate().get(YEAR) + "/" + this.getEndDate().getDate().get(MONTH) + "/" + this.getEndDate().getDate().get(DAY_OF_MONTH);}

    public String getEndTimeString(){
        String hour = Integer.toString(this.getEndDate().getHour());
        String minute = Integer.toString(this.getEndDate().getMinute());
        if (minute.length() == 1)
            minute = "0" + minute;
        return hour + ":" + minute;
    }

    public void editName(String newName) {this.name = newName;}

    public void editStartDate(String date, String time) throws DateOutOfBoundsException, TimeOutOfBoundsException {
        int year, month, day, hour, minute;
        try {
            Event.validateDate(date);
            String[] split = date.split("/");
            year = Integer.parseInt(split[0]);
            month = Integer.parseInt(split[1]);
            day = Integer.parseInt(split[2]);
        } catch (EventValidationException e){
            Log.d(TAG, "editStartDate() called with: date = [" + date + "]");
            year = 1900;
            month = 1;
            day = 1;
        }
        try {
            Event.validateTime(time);
            String[] split = time.split(":");
            hour = Integer.parseInt(split[0]);
            minute = Integer.parseInt(split[1]);
        } catch (EventValidationException e){
            Log.d(TAG, "editStartDate() called with: time = [" + time + "]");
            hour = 0;
            minute = 0;
        }
        this.editStartDate(year, month, day, hour, minute);
    }

    public void editStartDate(int year, int month, int day)throws DateOutOfBoundsException{
        this.startDate.editDate(year, month, day);
    }
    public void editStartDate(int year, int month, int day, int hour, int minute)throws DateOutOfBoundsException,TimeOutOfBoundsException{
        this.startDate.editDate(year, month, day, hour, minute);
    }

    public void editEndDate(String date, String time)throws DateOutOfBoundsException, TimeOutOfBoundsException {
        int year, month, day, hour, minute;
        try {
            Event.validateDate(date);
            String[] split = date.split("/");
            year = Integer.parseInt(split[0]);
            month = Integer.parseInt(split[1]);
            day = Integer.parseInt(split[2]);
        } catch (EventValidationException e){
            Log.d(TAG, "editEndDate() called with: date = [" + date + "]");
            year = 1900;
            month = 1;
            day = 1;
        }
        try {
            Event.validateTime(time);
            String[] split = time.split(":");
            hour = Integer.parseInt(split[0]);
            minute = Integer.parseInt(split[1]);
        } catch (EventValidationException e){
            Log.d(TAG, "editEndDate() called with: time = [" + time + "]");
            hour = 0;
            minute = 0;
        }
        this.editEndDate(year, month, day, hour, minute);
    }

    public void editEndDate(int year, int month, int day)throws DateOutOfBoundsException{
        this.endDate.editDate(year,month,day);
    }
    public void editEndDate(int year, int month, int day, int hour, int minute)throws DateOutOfBoundsException, TimeOutOfBoundsException {
        this.endDate.editDate(year,month,day, hour, minute);
    }
    public void editId (String newId){this.id = newId;}
    void editColour(String newColour){this.colour = newColour;}
    public void makeEventPublic() {this.isPublic = true;}
    public void makeEventPrivate() {this.isPublic = false;}

    public Event eventCopy(Event newEvent, int year, int month, int day)throws DateOutOfBoundsException, TimeOutOfBoundsException {
        newEvent.editName(this.name);
        newEvent.editStartDate(year, month, day, this.startDate.getHour(), this.startDate.getMinute());
        newEvent.editEndDate(year, month, day, this.endDate.getHour(), this.endDate.getMinute());
        newEvent.editId(this.id);
        newEvent.editColour(this.colour);
        if(this.isPublic){
            newEvent.makeEventPublic();
        }
        return newEvent;
    }
}
