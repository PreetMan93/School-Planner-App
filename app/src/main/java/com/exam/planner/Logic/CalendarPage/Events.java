package com.exam.planner.Logic.CalendarPage;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

class EventValidationException extends Exception{
    public EventValidationException(String message){
        super(message);
    }
}

class EventDateOutOfBoundsException extends EventValidationException{
    public EventDateOutOfBoundsException(String message){
        super(message);
    }
}

class EventDateInvalidFormatException extends EventValidationException{
    public EventDateInvalidFormatException(String message){
        super(message);
    }
}

class EventTimeOutOfBoundsException extends EventValidationException{
    public EventTimeOutOfBoundsException(String message){
        super(message);
    }
}

class EventTimeInvalidFormatException extends  EventValidationException{
    public EventTimeInvalidFormatException(String message){
        super(message);
    }
}


class Event
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

    public String getName() {return this.name;}
    public DateTime getStartDate() {return this.startDate;}
    public DateTime getEndDate() {return this.endDate;}
    public String getId() {return this.id;}
    public String getColour() {return this.colour;}
    public Boolean isPublic() {return this.isPublic;}

    public static void validateDate(String date) throws EventDateInvalidFormatException, EventDateOutOfBoundsException{
        String[] split = date.split("/");
        if (split.length != 3)
            throw(new EventDateInvalidFormatException("Date must follow the format YYYY/MM/DD"));
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        if (year < 1900)
            throw(new EventDateOutOfBoundsException("Date must begin after the year 1900"));
        if (month < 1 || month > 12)
            throw(new EventDateOutOfBoundsException("Month must be between 1 and 12"));
        if (day < 1 || day > 31)
            throw(new EventDateOutOfBoundsException("Day must be between 1 and 31"));
    }

    public static void validateTime(String time) throws EventTimeInvalidFormatException, EventTimeOutOfBoundsException {
        String[] split = time.split(":");
        if (split.length != 2)
            throw(new EventTimeInvalidFormatException("Time must follow the format HH:MM"));
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        if (hour < 0 || hour > 23)
            throw(new EventTimeOutOfBoundsException("Hour must be between 0 and 23"));
        if (minute < 0 || minute > 59)
            throw(new EventTimeOutOfBoundsException("Minute must be between 0 and 59"));
    }

    public String getStartDateString(){return this.getStartDate().getDate().YEAR + "/" + this.getStartDate().getDate().MONTH + "/" + this.getStartDate().getDate().DAY_OF_MONTH;}

    public String getStartTimeString(){return this.getStartDate().getHour() + ":" + this.getStartDate().getMinute();}

    public String getEndDateString(){return this.getEndDate().getDate().YEAR + "/" + this.getEndDate().getDate().MONTH + "/" + this.getEndDate().getDate().DAY_OF_MONTH;}

    public String getEndTimeString(){return this.getEndDate().getHour() + ":" + this.getEndDate().getMinute();}

    public void editName(String newName) {this.name = newName;}

    public void editStartDate(String date, String time){
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

    public void editStartDate(int year, int month, int day){
        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar newStartDate = new GregorianCalendar (year, month, day);
        if (now.before(newStartDate)){
            this.startDate.editDate(year,month,day);
            if(this.endDate.getDate().before(this.startDate.getDate())){
                this.endDate = this.startDate;
                this.endDate.addTime(1,0);
            }
        }
        else{
            System.out.println("You can't schedule an event prior to the current time.");
        }
    }
    public void editStartDate(int year, int month, int day, int hour, int minute){
        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar newStartDate = new GregorianCalendar (year, month, day, hour, minute);
        if (now.before(newStartDate)){
            this.startDate.editDate(year,month,day, hour, minute);
            if(this.endDate.getDate().before(this.startDate.getDate())){
                this.endDate = this.startDate;
                this.endDate.addTime(1,0);
            }
        }
        else{
            System.out.println("You can't schedule an event prior to the current time.");
        }
    }

    public void editEndDate(String date, String time){
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

    public void editEndDate(int year, int month, int day){
        GregorianCalendar newEndDate = new GregorianCalendar (year, month, day);
        if (newEndDate.after(this.startDate)){
            this.endDate.editDate(year,month,day);
        }
        else{
            System.out.println("You can't schedule an end time prior to the start time.");
        }
    }
    public void editEndDate(int year, int month, int day, int hour, int minute){
        GregorianCalendar newEndDate = new GregorianCalendar (year, month, day, hour, minute);
        if (newEndDate.after(this.startDate)){
            this.endDate.editDate(year,month,day, hour, minute);
        }
        else{
            System.out.println("You can't schedule an end time prior to the start time.");
        }
    }
    public void editId (String newId){this.id = newId;}
    public void editColour(String newColour){this.colour = newColour;}
    public void makeEventPublic() {this.isPublic = true;}
    public void makeEventPrivate() {this.isPublic = false;}

    public Event eventCopy(int year, int month, int day){
        Event newEvent = new Event();
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

class DateTime
{
    GregorianCalendar date;
    int hour, minute;
    TimeZone localTZ = TimeZone.getDefault();

    public DateTime(){
        GregorianCalendar now = new GregorianCalendar(localTZ);
        this.date = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        this.date.setTimeZone(localTZ);
        this.hour = now.get(Calendar.HOUR_OF_DAY);
        this.minute = 0;
    }
    public DateTime(int year, int month, int day){
        this.date = new GregorianCalendar(year, month, day);
        this.date.setTimeZone(localTZ);
        this.hour = 0;
        this.minute = 0;
    }
    public DateTime(int year, int month, int day, int hour, int minute){
        this.date = new GregorianCalendar(year,month,day,hour,minute);
        this.hour = hour;
        this.minute = minute;
    }
    DateTime(DateTime currentTime, int offsetHour, int offsetMinute){ //this method isn't public because it would require a lot of error checking otherwise
        this.date = new GregorianCalendar(currentTime.getDate().get(Calendar.YEAR), currentTime.getDate().get(Calendar.MONTH), currentTime.getDate().get(Calendar.DAY_OF_MONTH), currentTime.getHour() + offsetHour, currentTime.getMinute()+offsetMinute);
        this.date.setTimeZone(localTZ);
        this.hour = currentTime.getHour() + offsetHour;
        this.minute = currentTime.getMinute() + offsetMinute;
    }
    public GregorianCalendar getDate(){return date;}
    public int getHour(){return hour;}
    public int getMinute(){return minute;}
    public void editDate(int year, int month, int day){
        this.date = new GregorianCalendar(year, month, day, this.hour, this.minute);
        this.date.setTimeZone(localTZ);
    }
    public void editDate(int year, int month, int day, int hour, int minute){
        this.date = new GregorianCalendar(year,month,day,hour,minute);
        this.date.setTimeZone(localTZ);
        this.hour = hour;
        this.minute = minute;
    }
    public void editTime(int hour, int minute){
        GregorianCalendar newDate = new GregorianCalendar(this.date.get(Calendar.YEAR), this.date.get(Calendar.MONTH), this.date.get(Calendar.DAY_OF_MONTH), hour, minute);
        this.date = newDate;
        this.date.setTimeZone(localTZ);
        this.hour = hour;
        this.minute = minute;
    }
    void addTime(int hour, int minute){
        this.date.add((GregorianCalendar.HOUR_OF_DAY), hour);
        this.date.add((GregorianCalendar.MINUTE), minute);
        this.hour = this.date.get(Calendar.HOUR_OF_DAY);
        this.minute = this.date.get(Calendar.HOUR_OF_DAY);
    }
}

class EventManager
{
    ArrayList<Event> eventList;

    public EventManager(){
        this.eventList = new ArrayList(100);
    }

    //public ArrayList<Event>
}