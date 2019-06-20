package com.exam.planner.PersistenceTesting;

import com.exam.planner.DSO.User;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;

import org.junit.Test;

public class UserPersistenceStubTest {

    @Test
    public void testDoIExist(){
        UserPersistenceStub db = new UserPersistenceStub();
        assert(db.doIExist("12345"));
        assert(!db.doIExist("nope"));
    }

    @Test
    public void testAddUser(){
        UserPersistenceStub db = new UserPersistenceStub();
        db.addUser(new User("test", "test", "test"));
        assert(db.doIExist("test"));
    }

    @Test
    public void testRemoveUser(){
        UserPersistenceStub db = new UserPersistenceStub();
        assert(db.removeUser("12345"));
        assert(!db.removeUser("12345"));
    }
}
