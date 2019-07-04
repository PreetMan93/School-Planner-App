package com.exam.planner.Logic.Login.data;

import com.exam.planner.Logic.Events.DateTime;
import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;
import com.exam.planner.Persistence.IUserPersistence;

import java.util.ArrayList;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class Repository {

    private static volatile Repository instance;

    private DataSource dataSource;

    private LoggedInUser user = null;

    // private constructor : singleton access
    private Repository(IUserPersistence db) {
        this.dataSource = new DataSource(db);
    }

    public static Repository getInstance(IUserPersistence db) {
        if (instance == null) {
            instance = new Repository(db);
        }
        return instance;
    }

    public void notNew(){
        if(user != null){
            user.notNewUser();
        }
    }

    public LoggedInUser getUser() { return user; }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
    }

    public ArrayList<Event> getEvent(String id){
        ArrayList<Event> result = new ArrayList<>();

        for(Event e: user.getPlanner().getEventsList()){
            if(e.getId().equals(id))
                result.add(e);
        }
        return result;
    }

    public ArrayList<Event> getEvents(DateTime date){
        ArrayList<Event> result = new ArrayList<>();

        for(Event e: user.getPlanner().getEventsList()){
            if(e.getStartDate().getDay() == date.getDay() && e.getStartDate().getMonth() == date.getMonth())
                result.add(e);
        }
        return result;
    }

    public boolean attemptLogin(String username, String password){
        return dataSource.attemptLogin(username, password);
    }

    public Result<LoggedInUser> login(String username, String password) {
        // Pass the data to the class that interacts with the DB
        Result result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }

    public Result<LoggedInUser> register(String username, String password, String SQ, String SA) {
        // Pass the data to the class that interacts with the DB
        Result result = dataSource.register(username, password, SQ, SA);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }

}
