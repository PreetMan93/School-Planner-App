package com.exam.planner.DSO;

public class User {
    private String username;
    private String password;
    private String schedule;

    public User(){
        this.username = null;
        this.password = null;
        this.schedule = null;
    }

    public User(String un, String pwd, String sch){
        this.username = un;
        this.password = pwd;
        this.schedule = sch;
    }

    public String toString(){
        return "UserName: "+username + "\tPassword: "+password+"\tSchedule: "+schedule;
    }

    public String getUsername(){ return username; }
    public String getPassword() { return password; }
    public String getSchedule() { return schedule; }
}
