import java.util.*;

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
    void editColour(String newColour){this.colour = newColour;}
    public void makeEventPublic() {this.isPublic = true;}
    public void makeEventPrivate() {this.isPublic = false;}

    public Event eventCopy(Event newEvent, int year, int month, int day){
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
