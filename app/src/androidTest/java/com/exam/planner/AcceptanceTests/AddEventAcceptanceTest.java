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

public class AddEventAcceptanceTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testAddEvent() {
        try {
            onView(ViewMatchers.withId(R.id.username)).perform(clearText(), typeText("Username"));
            onView(withId(R.id.password)).perform(clearText(), typeText("Password"), closeSoftKeyboard());
            onView(withId(R.id.login)).perform(click());

            SystemClock.sleep(1000);
            onView(withId(R.id.add_event_button)).perform(click());
            onView(withId(R.id.event_edit_name_field)).perform(clearText(), typeText("Event 1"));
            onView(withId(R.id.event_edit_end_time_field)).perform(clearText(), typeText("11:10"));
            onView(withId(R.id.event_edit_start_time_field)).perform(clearText(), typeText("8:00"), closeSoftKeyboard());
            SystemClock.sleep(1000);
            onView(withId(R.id.sunday_checkbox)).perform(click());
            onView(withId(R.id.monday_checkbox)).perform(click());
            onView(withId(R.id.tuesday_checkbox)).perform(click());
            onView(withId(R.id.wednesday_checkbox)).perform(click());
            onView(withId(R.id.thursday_checkbox)).perform(click());
            onView(withId(R.id.friday_checkbox)).perform(click());
            onView(withId(R.id.saturday_checkbox)).perform(click());
            onView(withId(R.id.event_edit_repeat_date_field)).perform(clearText(), typeText("20191201"), closeSoftKeyboard());
            SystemClock.sleep(1000);
            onView(withId(R.id.event_edit_save_button)).perform(click());
        } catch (Exception e) {
            Assert.fail("Register acceptance test failed: " + e);
        }
    }
}
