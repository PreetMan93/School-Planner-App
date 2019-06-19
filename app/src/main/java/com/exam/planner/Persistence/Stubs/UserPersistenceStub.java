package com.exam.planner.Persistence.Stubs;

import com.exam.planner.DSO.User;
import com.exam.planner.Persistence.IUserPersistence;

import java.util.ArrayList;

public class UserPersistenceStub implements IUserPersistence {

    private ArrayList<User> users;

    public UserPersistenceStub(){
        this.users = new ArrayList<User>();
        users.add(new User("username", "password", "junk"));
        users.add(new User("Username", "Password", "junk"));
    }

    @Override
    public void addUser(User newUsr){
        if(!doIExist(newUsr.getUsername(), newUsr.getPassword()))
            users.add(newUsr);
    }

    @Override
    public boolean removeUser(User user){
        int index;
        if((index = indexOfUser(user.getUsername(), user.getPassword())) != -1) {
            users.remove(index);
            return true;
        }
        return false;
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
    public boolean doIExist(String username, String password){
        return indexOfUser(username, password) != -1;
    }

}
