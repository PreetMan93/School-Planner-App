package com.exam.planner.Logic.Dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class DBSetup {
    ArrayList<User> users;
    Gson gs;

    public DBSetup(){
        gs  = new Gson();
        users = new ArrayList<User>();
    }

    public void readDB(){
        try {
            JsonReader jr = new JsonReader(new FileReader("db.json"));
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            users = gs.fromJson(jr, type);
            jr.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean vaild(String user, String password){
        //public boolean exist = false;
        for(User u:users){
            if(u.username==user && u.password==password)
                return true;
        }
        return false;

    }
    public void writeDB(){
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("db.json"));
            pr.println(gs.toJson(users));
            pr.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void add(User user){
        users.add(user);
    }

    public String toString(){
        String result = "";
        for(User u : users){
            result += u.toString() + "\n";
        }
        return result;
    }

}
