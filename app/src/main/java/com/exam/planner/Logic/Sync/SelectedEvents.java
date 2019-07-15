package com.exam.planner.Logic.Sync;

import com.exam.planner.Logic.Events.Event;

import java.util.ArrayList;

public class SelectedEvents {

    private ArrayList<Event> events;

    public SelectedEvents(){
        events = new ArrayList<>();
    }

    public boolean contains(String id){
        for(Event e: events){
            if(e.getId().equals(id))
                return true;
        }
        return false;
    }

    public void add(Event e){events.add(e);}
    public void remove(Event e){events.remove(e);}
    public ArrayList<Event> getEvents() { return events; }
}
