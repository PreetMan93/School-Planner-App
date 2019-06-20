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
    public void removeEvent(Event e){
        eventsList.remove(e);
    }

    public boolean eventListContains(Event e){
        for (Event element : eventsList){
            if(e.compare(element))
                return true;
        }
        return false;
    }
}
