package com.exam.planner;

import com.exam.planner.Logic.CalendarFormatterTest;
import com.exam.planner.DSO.PlannerTest;
import com.exam.planner.DSO.UserTest;
import com.exam.planner.Logic.Events.EventTest;
import com.exam.planner.LoginTesting.DataSourceUnitTest;
import com.exam.planner.LoginTesting.FormStateManagerTest;
import com.exam.planner.LoginTesting.FormStateTest;
import com.exam.planner.LoginTesting.LoginViewModelFactoryTest;
import com.exam.planner.Integration.LoginViewModelTest;
import com.exam.planner.LoginTesting.RepositoryTest;
import com.exam.planner.LoginTesting.ResultTest;
import com.exam.planner.Integration.UserPersistenceStubTest;
import com.exam.planner.SyncTesting.SelectedEventsTest;

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
        FormStateTest.class,
        LoginViewModelTest.class,
        RepositoryTest.class,
        ResultTest.class,
        FormStateManagerTest.class,
        CalendarFormatterTest.class,
        SelectedEventsTest.class
})
public class AllTests
{

}
