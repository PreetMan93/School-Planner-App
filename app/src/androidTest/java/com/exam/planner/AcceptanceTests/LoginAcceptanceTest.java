package com.exam.planner.AcceptanceTests;

import android.os.SystemClock;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.exam.planner.Presentation.LoginAndRegister.LoginActivity;
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

public class LoginAcceptanceTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testLogin() {
        try {
            onView(ViewMatchers.withId(R.id.username)).perform(clearText(), typeText("Username"));
            onView(withId(R.id.password)).perform(clearText(), typeText("Password"), closeSoftKeyboard());
            onView(withId(R.id.saveInfo)).perform(click());
            SystemClock.sleep(1000);
            onView(withId(R.id.saveInfo)).perform(click());
            onView(withId(R.id.login)).perform(click());
            SystemClock.sleep(1000);
        } catch (Exception e) {
            Assert.fail("Login acceptance test failed: " + e);
        }
    }
}
