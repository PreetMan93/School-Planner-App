package com.exam.planner.DSO;


import com.exam.planner.Logic.Events.Event;

import java.util.ArrayList;
import java.util.Iterator;

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
        boolean ret = false;
        for (Iterator<Event> iterator = eventsList.iterator(); iterator.hasNext();) {
            Event next = iterator.next();
            if (next.getId() != null && next.getId().equals(id)){
                iterator.remove();
                ret = true;
            }
        }
        return ret;
    }

    public boolean removeEventCopies(String copyId){
        boolean ret = false;
        for (Iterator<Event> iterator = eventsList.iterator(); iterator.hasNext();) {
            Event next = iterator.next();
            if (next.getCopyId() != null && next.getCopyId().equals(copyId)){
                iterator.remove();
                ret = true;
            }
        }
        return ret;
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
