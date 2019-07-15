package com.exam.planner.DSO;


import android.util.Log;

import com.exam.planner.Logic.Events.Event;
import java.util.ArrayList;

public class Planner {

    private ArrayList<Event> eventsList;
    private String id;

    public Planner(String id){
        eventsList = new ArrayList<>();
        this.id = id;
    }

    public String getId(){ return id; }

    public void addEvent(Event e){
        eventsList.add(e);
    }

    public boolean removeEvent(String id){
        int i = 0;
        for(Event e: eventsList) {
            if(e.getId().equals(id)) {
                eventsList.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean eventListContains(Event e){
        for (Event element : eventsList){
            if(e.compare(element))
                return true;
        }
        return false;
    }

    public Event getEvent(String id){
        for (Event e: eventsList)
            if (e.getId().equals(id))
                return e;
        return null;
    }

    public ArrayList<Event> getEventsList(){ return eventsList; }

    public ArrayList<Event> getEventsList(int year, int month, int day) {
        ArrayList<Event> retList = new ArrayList<>();
        for (Event e: eventsList)
            if (e.getStartYear() == year && e.getStartMonth() == month && e.getStartDay() == day)
                retList.add(e);
        return retList;
    }
}
