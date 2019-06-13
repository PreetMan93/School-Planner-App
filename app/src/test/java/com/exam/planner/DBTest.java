package com.exam.planner;

import com.exam.planner.Logic.Dao.DBSetup;
import com.exam.planner.Logic.Dao.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DBTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testValidateInformation(){
        DBSetup db = new DBSetup();
        String user = "username";
        String pass = "password";
        assert(db.vaildInformation(user,pass));
    }

    @Test
    public void testDB_Write(){
        DBSetup db = new DBSetup();
        db.add(new User("username_0", "pwd_0", "test"));
        db.add(new User("username_1", "pwd_1", "test1"));
        db.add(new User("username_2", "pwd_2", "test2"));
        db.add(new User("username_3", "pwd_3", "test3"));
        db.add(new User("username", "password", "testSchedule"));
        db.writeDB();
    }
    @Test
    public void testDB_Read(){
        DBSetup db = new DBSetup();
        db.readDB();
        System.out.println(db.toString());
    }
}