package com.exam.planner.DSO.Events;

import java.util.*;

class DateTime
{
    private GregorianCalendar date;
    private int hour, minute;

    public DateTime(){
        GregorianCalendar now = new GregorianCalendar(TimeZone.getDefault());
        this.date = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.HOUR_OF_DAY), 0);
        this.date.setTimeZone(TimeZone.getDefault());
        this.hour = now.get(Calendar.HOUR_OF_DAY);
        this.minute = 0;
    }
    public DateTime(int year, int month, int day, int hour, int minute){
        this.date = new GregorianCalendar(year,month-1,day,hour,minute);
        this.hour = hour;
        this.minute = minute;
    }
    DateTime(DateTime currentTime, int offsetHour, int offsetMinute){ //this method isn't public because it would require a lot of error checking otherwise
        this.date = new GregorianCalendar(currentTime.getDate().get(Calendar.YEAR), currentTime.getDate().get(Calendar.MONTH), currentTime.getDate().get(Calendar.DAY_OF_MONTH), currentTime.getHour() + offsetHour, currentTime.getMinute()+offsetMinute);
        this.date.setTimeZone(TimeZone.getDefault());
        this.hour = currentTime.getHour() + offsetHour;
        this.minute = currentTime.getMinute() + offsetMinute;
    }
    public GregorianCalendar getDate(){return date;}
    public int getYear(){return date.get(Calendar.YEAR);}
    public int getMonth(){return date.get(Calendar.MONTH) + 1;}  //This looks dumb because Gregorian Calendar is dumb. For some reason ONLY MONTHS start counting from 0
    public int getDay(){return date.get(Calendar.DAY_OF_MONTH);}
    public int getHour(){return hour;}
    public int getMinute(){return minute;}
    public void printDate(){
        System.out.print(this.getMonth()+"/"+this.getDay()+ "/"+this.getYear()+ "  ");
        System.out.println(this.getHour()+":"+this.getMinute());
    }
    public void editDate(int year, int month, int day){
        this.date = new GregorianCalendar(year, month-1, day, this.hour, this.minute);
        this.date.setTimeZone(TimeZone.getDefault());
    }
    public void editDate(int year, int month, int day, int hour, int minute){
        this.date = new GregorianCalendar(year,month-1,day,hour,minute);
        this.date.setTimeZone(TimeZone.getDefault());
        this.hour = hour;
        this.minute = minute;
    }
    public void editTime(int hour, int minute){
        GregorianCalendar newDate = new GregorianCalendar(this.date.get(Calendar.YEAR), this.date.get(Calendar.MONTH), this.date.get(Calendar.DAY_OF_MONTH), hour, minute);
        this.date = newDate;
        this.date.setTimeZone(TimeZone.getDefault());
        this.hour = hour;
        this.minute = minute;
    }
    public void dateCopy(DateTime eventToCopy){
        this.date = new GregorianCalendar(eventToCopy.getYear(),eventToCopy.getMonth(),eventToCopy.getDay(),eventToCopy.getHour(),eventToCopy.getMinute());
        this.date.setTimeZone(TimeZone.getDefault());
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