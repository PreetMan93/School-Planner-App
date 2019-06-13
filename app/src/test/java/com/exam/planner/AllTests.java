package com.exam.planner;

import com.exam.planner.DSO.Events.EventTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginDataSourceUnitTest.class,
        EventTest.class
})
public class AllTests
{

}
