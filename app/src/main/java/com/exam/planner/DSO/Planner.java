package com.exam.planner.DSO;


//import com.exam.planner.DSO.Events
import com.exam.planner.DSO.Events.Event;
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
        for(Event e: eventsList) {
            if(e.getId().equals(id)) {
                eventsList.remove(e);
                return true;
            }
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

    public ArrayList<Event> getEventsList(){ return eventsList; }
}
