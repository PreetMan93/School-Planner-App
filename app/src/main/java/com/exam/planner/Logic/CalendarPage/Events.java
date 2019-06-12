package com.exam.planner.Logic.CalendarPage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

class Event
{
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

    public void editName(String newName) {this.name = newName;}
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