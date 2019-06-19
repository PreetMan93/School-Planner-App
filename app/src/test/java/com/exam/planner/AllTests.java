package com.exam.planner;

import com.exam.planner.DSO.Events.EventTest;
import com.exam.planner.PersistenceTesting.UserPersistenceStubTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginDataSourceUnitTest.class,
        EventTest.class,
        UserPersistenceStubTest.class
})
public class AllTests
{

}
