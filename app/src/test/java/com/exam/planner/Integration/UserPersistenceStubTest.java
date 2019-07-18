package com.exam.planner.Integration;

import com.exam.planner.DSO.User;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;

import org.junit.Test;
import org.junit.Assert;

public class UserPersistenceStubTest {

    @Test
    public void testDoIExist(){
        UserPersistenceStub db = UserPersistenceStub.getInstance();
        db.addUser(new User("9090","username", "password"));
        Assert.assertTrue("Didn't find existing id", db.doIExist("username", "password"));
        Assert.assertTrue("Found non existing id", !db.doIExist("nope", "nope"));
    }

    @Test
    public void testAddUser(){
        UserPersistenceStub db = UserPersistenceStub.getInstance();
        db.addUser(new User("test", "test", "test"));
        Assert.assertTrue("User wasn't successfully added", db.doIExist("test", "test"));
    }

    @Test
    public void testRemoveUser(){
        UserPersistenceStub db = UserPersistenceStub.getInstance();
        Assert.assertTrue("User not successfully removed", db.removeUser("12345"));
        Assert.assertTrue("Removed non existent user", !db.removeUser("12345"));
    }
}
