package com.exam.planner.AcceptanceTests;

import android.os.SystemClock;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import com.exam.planner.Presentation.LoginAndRegister.RegisterActivity;
import com.exam.planner.R;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegisterAcceptanceTest {

    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void testRegister() {
        try {
            onView(ViewMatchers.withId(R.id.registerEmail)).perform(clearText(), typeText("NewUser"));
            onView(withId(R.id.registerPassword)).perform(clearText(), typeText("password"));
            onView(withId(R.id.registerConfirmPassword)).perform(clearText(), typeText("password"));
            onView(withId(R.id.registerSecretQuestion)).perform(typeText("Super Secret Question"));
            onView(withId(R.id.registerSecretAnswer)).perform(typeText("Super Secret Answer"), closeSoftKeyboard());
            SystemClock.sleep(1000);
            onView(withId(R.id.registerButton)).perform(click());
            SystemClock.sleep(1000);
        } catch (Exception e) {
            Assert.fail("Register acceptance test failed: " + e);
        }
    }
}