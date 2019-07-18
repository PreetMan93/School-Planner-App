package com.exam.planner.Persistence;

import com.exam.planner.DSO.User;

import java.util.ArrayList;

public interface IUserPersistence {

    void addUser(User newUsr);
    boolean removeUser(String id);
    //boolean doIExist(String username);
    boolean doIExist(String username, String password);
    User getUser(String username, String password);
    ArrayList<User> getUsers();
}
