package com.exam.planner;

import com.exam.planner.DSO.Events.EventTest;
import com.exam.planner.DSO.PlannerTest;
import com.exam.planner.DSO.UserTest;
import com.exam.planner.LoginTesting.FormStateTest;
import com.exam.planner.LoginTesting.LoginViewModelFactoryTest;
import com.exam.planner.PersistenceTesting.UserPersistenceStubTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataSourceUnitTest.class,
        EventTest.class,
        UserPersistenceStubTest.class,
        PlannerTest.class,
        UserTest.class,
        LoginViewModelFactoryTest.class,
        FormStateTest.class
})
public class AllTests
{

}
