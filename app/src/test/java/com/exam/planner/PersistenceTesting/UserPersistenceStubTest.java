package com.exam.planner.PersistenceTesting;

import com.exam.planner.DSO.User;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;

import org.junit.Test;

public class UserPersistenceStubTest {

    @Test
    public void testDoIExist(){
        UserPersistenceStub db = new UserPersistenceStub();
        assert(db.doIExist("username", "password"));
        assert(!db.doIExist("garbage", "trash"));
    }

    @Test
    public void testAddUser(){
        UserPersistenceStub db = new UserPersistenceStub();
        db.addUser(new User("test", "test", "test"));
        assert(db.doIExist("test", "test"));
    }

    @Test
    public void testRemoveUser(){
        UserPersistenceStub db = new UserPersistenceStub();
        assert(db.removeUser(new User("username", "password", "junk")));
        assert(!db.removeUser(new User("username", "password", "junk")));
    }
}
