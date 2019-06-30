package com.exam.planner.Persistence;

import com.exam.planner.DSO.User;

public interface IUserPersistence {

    void addUser(User newUsr);
    boolean removeUser(String id);
    boolean doIExist(String username);
    boolean doIExist(String username, String password);
}
