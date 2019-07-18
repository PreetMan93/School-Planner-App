package com.exam.planner;

import com.exam.planner.AcceptanceTests.AddEventAcceptanceTest;
import com.exam.planner.AcceptanceTests.DeleteEventAcceptanceTest;
import com.exam.planner.AcceptanceTests.LoginAcceptanceTest;
import com.exam.planner.AcceptanceTests.RegisterAcceptanceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginAcceptanceTest.class,
        RegisterAcceptanceTest.class,
        AddEventAcceptanceTest.class,
        DeleteEventAcceptanceTest.class
})

public class AllAcceptanceTests {
}
