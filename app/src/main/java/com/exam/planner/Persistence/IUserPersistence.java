package com.exam.planner.Persistence;

import com.exam.planner.DSO.User;

public interface IUserPersistence {

    void addUser(User newUsr);
    boolean removeUser(User user);
    boolean doIExist(String username, String password);
}
