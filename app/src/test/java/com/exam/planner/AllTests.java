package comp3350.srsys.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import app\src\test\java\com\exam\planner\DBTest.java;
import app\src\test\java\com\exam\planner\LoginDataSourceUnitTest.java;
import app\src\test\java\com\exam\planner\DSO\Events\EventTest.java;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DBTest.class,
        LoginDataSourceUnitTest.class,
        EventTest.class
})
public class AllTests
{

}
