package com.exam.planner.Persistence.Stubs;

import com.exam.planner.DSO.User;
import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Persistence.IUserPersistence;

import java.util.ArrayList;

public class UserPersistenceStub implements IUserPersistence {

    private ArrayList<User> users;

    public UserPersistenceStub(){
        this.users = new ArrayList<User>();
        users.add(new User("12345", "username", "password"));
        users.add(new User("54321", "Username", "Password"));

        Event event1 = new Event("1234");
        users.get(0).addEvent(event1);
        users.get(1).addEvent(event1);
    }

    @Override
    public void addUser(User newUsr){
        if(!doIExist(newUsr.getUsername(), newUsr.getPassword()))
            users.add(newUsr);
    }

    public void displayUsers(){
        for (User u: users) System.out.println(u.toString());
    }

    /*@Override
    public boolean removeUser(User user){
        int index;
        if((index = indexOfUser(user.getId())) != -1) {
            users.remove(index);
            return true;
        }
        return false;
    }*/

    @Override
    public boolean removeUser(String id){
        int index;
        if((index = indexOfUserID(id)) != -1) {
            users.remove(index);
            return true;
        }
        return false;
    }

    /*private int indexOfUserUsername(String username){
        int index = 0;
        for(User u: users) {
            if(u.getUsername().equals(username))
                return index;
            index++;
        }
        return -1;
    }*/

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
