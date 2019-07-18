package com.exam.planner.Logic.Events;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTime
{
    private static final String TAG = "DateTime";
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
        this.date = new GregorianCalendar(year,month,day,hour,minute);
        this.hour = hour;
        this.minute = minute;
    }
    DateTime(DateTime currentTime, int offsetHour, int offsetMinute){
        this.date = new GregorianCalendar(currentTime.getDate().get(Calendar.YEAR), currentTime.getDate().get(Calendar.MONTH), currentTime.getDate().get(Calendar.DAY_OF_MONTH), currentTime.getHour() + offsetHour, currentTime.getMinute()+offsetMinute);
        this.date.setTimeZone(TimeZone.getDefault());
        this.hour = currentTime.getHour() + offsetHour;
        this.minute = currentTime.getMinute() + offsetMinute;
    }
    public GregorianCalendar getDate(){return date;}
    public int getYear(){return date.get(Calendar.YEAR);}
    public int getMonth(){return date.get(Calendar.MONTH);}
    public int getDay(){return date.get(Calendar.DAY_OF_MONTH);}
    public int getHour(){return hour;}
    public int getMinute(){return minute;}
    public void printDate(){
        System.out.print(this.getMonth()+"/"+this.getDay()+ "/"+this.getYear()+ "  ");
        System.out.println(this.getHour()+":"+this.getMinute());
    }
    public void editDate(int year, int month, int day) throws DateOutOfBoundsException{
        try {
            this.validateDate(year, month, day);
        }
        catch (DateTimeValidationException e){
            throw(e);
        }
        this.date = new GregorianCalendar(year, month, day, this.hour, this.minute);
        this.date.setTimeZone(TimeZone.getDefault());
    }
    public void editDate(int year, int month, int day, int hour, int minute) throws DateOutOfBoundsException, TimeOutOfBoundsException{
        try {
            this.validateDate(year, month, day);
            this.validateTime(hour,minute);
        }
        catch (DateTimeValidationException e){
            throw(e);
        }
        this.date = new GregorianCalendar(year,month,day,hour,minute);
        this.date.setTimeZone(TimeZone.getDefault());
        this.hour = hour;
        this.minute = minute;
    }
    public void editTime(int hour, int minute)throws TimeOutOfBoundsException{
        try {
            this.validateTime(hour,minute);
        }
        catch (DateTimeValidationException e){
            throw (e);
        }
        GregorianCalendar newDate = new GregorianCalendar(this.date.get(Calendar.YEAR), this.date.get(Calendar.MONTH), this.date.get(Calendar.DAY_OF_MONTH), hour, minute);
        this.date = newDate;
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
    public static void validateDate(int year, int month, int day) throws DateOutOfBoundsException {
        if (year < 1900)
            throw(new DateOutOfBoundsException("Date must begin after the year 1900"));
        if (month < 0 || month > 11)
            throw(new DateOutOfBoundsException("Month must be between 0 and 11"));
        if (day < 1 || day > 31)
            throw(new DateOutOfBoundsException("Day must be between 1 and 31"));
    }
    public static void validateTime(int hour, int minute) throws TimeOutOfBoundsException {
        if (hour < 0 || hour > 23)
            throw(new TimeOutOfBoundsException("Hour must be between 0 and 23"));
        if (minute < 0 || minute > 59)
            throw(new TimeOutOfBoundsException("Minute must be between 0 and 59"));
    }

    public static void validateEndAfterStart(int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute) throws DateTimeValidationException {
        //Nested 'if' is because each successive time increment only needs to be checked if all previous time increments are equal
        if (startYear > endYear)
            throw(new DateTimeValidationException("Start date must be before end date (Year)"));
        if (startYear == endYear) {
            if (startMonth > endMonth)
                throw(new DateTimeValidationException("Start date must be before end date (Month)"));
            if (startMonth == endMonth) {
                if (startDay > endDay)
                    throw(new DateTimeValidationException("Start date must be before end date (Day)"));
                if (startDay == endDay) {
                    if (startHour > endHour)
                        throw(new DateTimeValidationException("Start time must be before end time (Hour)"));
                    if (startHour == endHour && startMinute >= endMinute)
                        throw(new DateTimeValidationException("Start time must be before end time (Minute)"));
                }
            }
        }
    }
}