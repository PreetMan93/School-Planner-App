package com.exam.planner.Logic.Dao;

public class User {
    String username;
    String password;
    String schedule;

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

}
