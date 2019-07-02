package com.exam.planner.DSO;

import com.exam.planner.DSO.Events.Event;

public class User {
    private String id;
    private String username;
    private String password;
    private String SQ;
    private String SA;
    private Planner planner;

    public User(String id, String un, String pwd){
        this.id = id;
        this.username = un;
        this.password = pwd;
        this.SQ = "";
        this.SA = "";
        this.planner = new Planner(id);
    }

    public User(String id, String un, String pwd, String SQ, String SA){
        this.id = id;
        this.username = un;
        this.password = pwd;
        this.SQ = SQ;
        this.SA = SA;
        this.planner = new Planner(id);
    }

    public String toString(){ return "ID: " + id + " Username: " + username; }

    public String getUsername(){ return username; }
    public String getPassword() { return password; }
    public String getId() { return id; }
    public String getSA() { return SA; }
    public String getSQ() { return SQ; }
    public Planner getPlanner() { return planner; }

    public void addEvent(Event e){ planner.addEvent(e); }
    public boolean removeEvent(String id){ return planner.removeEvent(id); }
    public boolean isDuplicate(Event e){ return planner.eventListContains(e); }
}
