package com.exam.planner.Persistence.Stubs;

import com.exam.planner.DSO.User;
import com.exam.planner.Logic.Events.DateOutOfBoundsException;
import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Persistence.IUserPersistence;

import java.util.ArrayList;

public class UserPersistenceStub implements IUserPersistence {

    private ArrayList<User> users;


    private static volatile UserPersistenceStub instance;

    // private constructor : singleton access
    private UserPersistenceStub() {
        this.users = new ArrayList<User>();
        users.add(new User("12345", "username", "password"));
        users.add(new User("54321", "Username", "Password"));
        users.add(new User("00001", "username1", "password1"));
        users.get(2).addEvent(new Event(java.util.UUID.randomUUID().toString()));
        for(int i = 0; i < 5; i ++) {
            users.get(0).addEvent(new Event(java.util.UUID.randomUUID().toString()));
        }

        for(int i = 0; i < 10; i ++) {
            users.get(1).addEvent(new Event(java.util.UUID.randomUUID().toString()));
        }


        try {
            for(int i = 0; i < 3; i++) {
                Event e = new Event(java.util.UUID.randomUUID().toString());
                e.editStartDate(e.getStartYear(), e.getStartMonth(), (e.getStartDay() + 1) % 29);
                e.editEndDate(e.getEndYear(), e.getEndMonth(), (e.getEndDay() + 1) % 29);
                users.get(0).addEvent(e);
                users.get(1).addEvent(e);
            }
        } catch (DateOutOfBoundsException exp){
            System.out.println(""+exp);
        }
    }

    public static UserPersistenceStub getInstance() {
        if (instance == null) {
            instance = new UserPersistenceStub();
        }
        return instance;
    }

    @Override
    public ArrayList<User> getUsers(){return users;}

    @Override
    public void addUser(User newUsr){
        if(!doIExist(newUsr.getUsername(), newUsr.getPassword()))
            users.add(newUsr);
    }

    public void displayUsers(){
        for (User u: users) System.out.println(u.toString());
    }

    @Override
    public boolean removeUser(String id){
        int index;
        if((index = indexOfUserID(id)) != -1) {
            users.remove(index);
            return true;
        }
        return false;
    }

    private int indexOfUserID(String id){
        int index = 0;
        for(User u: users) {
            if(u.getId().equals(id))
                return index;
            index++;
        }
        return -1;
    }

    private int indexOfUser(String username, String password){
        int index = 0;
        for(User u: users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
                return index;
            index++;
        }
        return -1;
    }

    @Override
    public User getUser(String username, String password) {
        return users.get(indexOfUser(username, password));
    }

    /*@Override
    public boolean doIExist(String username){
        return indexOfUserUsername(username) != -1;
    }*/

    @Override
    public boolean doIExist(String username, String password){
        return indexOfUser(username, password) != -1;
    }

}
