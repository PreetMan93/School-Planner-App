package com.exam.planner.Logic.Events;

public class Event {
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

    public int getStartYear() {return this.getStartDate().getYear();}
    public int getStartMonth() {return this.getStartDate().getMonth();}
    public int getStartDay() {return this.getStartDate().getDay();}
    public int getStartHour() {return this.getStartDate().getHour();}
    public int getStartMinute() {return this.getStartDate().getMinute();}

    public int getEndYear() {return this.getEndDate().getYear();}
    public int getEndMonth() {return this.getEndDate().getMonth();}
    public int getEndDay() {return this.getEndDate().getDay();}
    public int getEndHour() {return this.getEndDate().getHour();}
    public int getEndMinute() {return this.getEndDate().getMinute();}

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

    public boolean endDatePriorToStart() {
        return this.getEndDate().getDate().before(this.getStartDate().getDate());
    }

    public static void validateDate(int year, int month, int day) throws DateOutOfBoundsException {
        if (year < 1900)
            throw(new DateOutOfBoundsException("Date must begin after the year 1900"));
        if (month < 1 || month > 12)
            throw(new DateOutOfBoundsException("Month must be between 1 and 12"));
        if (day < 1 || day > 31)
            throw(new DateOutOfBoundsException("Day must be between 1 and 31"));
    }

    public static void validateTime(int hour, int minute) throws TimeOutOfBoundsException {
        if (hour < 0 || hour > 23)
            throw(new TimeOutOfBoundsException("Hour must be between 0 and 23"));
        if (minute < 0 || minute > 59)
            throw(new TimeOutOfBoundsException("Minute must be between 0 and 59"));
    }

    public static void validateEndAfterStart(int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute) throws EventValidationException {
        //Nested 'if' is because each successive time increment only needs to be checked if all previous time increments are equal
        if (startYear > endYear)
            throw(new EventValidationException("Start date must be before end date (Year)"));
        if (startYear == endYear) {
            if (startMonth > endMonth)
                throw(new EventValidationException("Start date must be before end date (Month)"));
            if (startMonth == endMonth) {
                if (startDay > endDay)
                    throw(new EventValidationException("Start date must be before end date (Day)"));
                if (startDay == endDay) {
                    if (startHour > endHour)
                        throw(new EventValidationException("Start time must be before end time (Hour)"));
                    if (startHour == endHour && startMinute >= endMinute)
                        throw(new EventValidationException("Start time must be before end time (Minute)"));
                }
            }
        }
    }

    public void editName(String newName) {this.name = newName;}

    public void editStartDate(int year, int month, int day) throws DateOutOfBoundsException{
        this.startDate.editDate(year, month, day);
    }
    public void editStartDate(int year, int month, int day, int hour, int minute)throws DateOutOfBoundsException,TimeOutOfBoundsException{
        this.startDate.editDate(year, month, day, hour, minute);
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
