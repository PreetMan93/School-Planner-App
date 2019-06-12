import java.util.*;

class DateTime
{
    private GregorianCalendar date;
    private int hour, minute;
    private TimeZone localTZ = TimeZone.getDefault();

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
    public int getYear(){return date.get(Calendar.YEAR);}
    public int getMonth(){return date.get(Calendar.MONTH);}
    public int getDay(){return date.get(Calendar.DAY_OF_MONTH);}
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