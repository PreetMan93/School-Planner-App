package com.exam.planner.DSO;

import com.exam.planner.DSO.Events.Event;

public class User {
    private String id;
    private String username;
    private String password;
    private Planner planner;

    public User(String id){
        this.id = id;
        this.username = null;
        this.password = null;
        this.planner = new Planner(id);
    }

    public User(String id, String un, String pwd){
        this.id = id;
        this.username = un;
        this.password = pwd;
        this.planner = new Planner(id);
    }

    public String toString(){
        return "ID: " + id + " Username: " + username;
    }

    public String getUsername(){ return username; }
    public String getPassword() { return password; }
    public String getId() { return id; }

    public void addEvent(Event e){ planner.addEvent(e); }
    public void removeEvent(Event e){ planner.removeEvent(e); }
    public void dupEvent(Event e){ planner.eventListContains(e); }
}
