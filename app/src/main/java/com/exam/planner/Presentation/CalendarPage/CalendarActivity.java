package com.exam.planner.Presentation.CalendarPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.exam.planner.Logic.Events.DateOutOfBoundsException;
import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Events.TimeOutOfBoundsException;
import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;
import com.exam.planner.Presentation.Settings.SettingsActivity;
import com.exam.planner.R;

import java.util.ArrayList;
import java.util.Calendar;

import static com.exam.planner.Logic.Login.data.Repository.getInstance;

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";

    private EventListAdapter adapter;
    private Repository repo;

    private int sYear, sMonth, sDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Log.d(TAG, "onCreate: started");

        repo = getInstance(new UserPersistenceStub());

        final TabLayout navigationBar = findViewById(R.id.NavBar);

        final Intent settingsIntent = new Intent(this, SettingsActivity.class);

        final CalendarView calendarView = findViewById(R.id.calendar_view);
        final Button addEventButton = findViewById(R.id.add_event_button);

        Calendar today = Calendar.getInstance();
        sYear = today.get(Calendar.YEAR);
        sMonth = today.get(Calendar.MONTH);
        sDay = today.get(Calendar.DAY_OF_MONTH);
        calendarView.setDate(today.getTimeInMillis(), true, true);

        refreshEventListView(repo.getEvents(sYear, sMonth, sDay));


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                sYear = year;
                sMonth = month;
                sDay = dayOfMonth;
                refreshEventListView(repo.getEvents(year, month, dayOfMonth));
            }
        });

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                Event e = new Event(sYear, sMonth, sDay, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE));

                Intent editEventIntent = new Intent(v.getContext(), EventEditActivity.class);
                editEventIntent.putExtra("eventId", "-1");
                editEventIntent.putExtra("eventName", e.getName());

                editEventIntent.putExtra("eventStartYear", e.getStartYear());
                editEventIntent.putExtra("eventStartMonth", e.getStartMonth());
                editEventIntent.putExtra("eventStartDay", e.getStartDay());
                editEventIntent.putExtra("eventStartHour", e.getStartHour());
                editEventIntent.putExtra("eventStartMinute", e.getStartMinute());

                editEventIntent.putExtra("eventEndYear", e.getEndYear());
                editEventIntent.putExtra("eventEndMonth", e.getEndMonth());
                editEventIntent.putExtra("eventEndDay", e.getEndDay());
                editEventIntent.putExtra("eventEndHour", e.getEndHour());
                editEventIntent.putExtra("eventEndMinute", e.getEndMinute());

                ((Activity)v.getContext()).startActivityForResult(editEventIntent, 1);
            }
        });

        navigationBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getText().equals("Settings")){
                    startActivity(settingsIntent);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: CalendarActivity is gone!");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Calendar focusDay = Calendar.getInstance();
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                String eventId = data.getStringExtra("eventId");
                String eventName = data.getStringExtra("eventName");

                int startYear = data.getIntExtra("eventStartYear", 1900);
                int startMonth = data.getIntExtra("eventStartMonth", 1);
                int startDay = data.getIntExtra("eventStartDay", 1);
                int startHour = data.getIntExtra("eventStartHour", 0);
                int startMinute = data.getIntExtra("eventStartMinute", 0);

                int endYear = data.getIntExtra("eventEndYear", 1900);
                int endMonth = data.getIntExtra("eventEndMonth", 1);
                int endDay = data.getIntExtra("eventEndDay", 1);
                int endHour = data.getIntExtra("eventEndHour", 0);
                int endMinute = data.getIntExtra("eventEndMinute", 0);

                focusDay.set(startYear, startMonth, startDay);

                Event editEvent;
                if (!eventId.equals("-1"))
                    editEvent = repo.getUser().getPlanner().getEvent(eventId);
                else {
                    editEvent = new Event(java.util.UUID.randomUUID().toString());
                    repo.addEvent(editEvent);
                }
                
                editEvent.editName(eventName);
                try {
                    editEvent.editStartDate(startYear, startMonth, startDay, startHour, startMinute);
                } catch (DateOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (TimeOutOfBoundsException e) {
                    e.printStackTrace();
                }
                try {
                    editEvent.editEndDate(endYear, endMonth, endDay, endHour, endMinute);
                } catch (DateOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (TimeOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == 2){
                String eventId = data.getStringExtra("eventId");
                int startYear = data.getIntExtra("eventStartYear", 1900);
                int startMonth = data.getIntExtra("eventStartMonth", 1);
                int startDay = data.getIntExtra("eventStartDay", 1);

                focusDay.set(startYear, startMonth, startDay);
                repo.getUser().getPlanner().removeEvent(eventId);
            }
        }
        sYear = focusDay.get(Calendar.YEAR);
        sMonth = focusDay.get(Calendar.MONTH);
        sDay = focusDay.get(Calendar.DAY_OF_MONTH);
        CalendarView calendarView = findViewById(R.id.calendar_view);
        calendarView.setDate(focusDay.getTimeInMillis(), true, true);
        refreshEventListView(repo.getEvents(focusDay.get(Calendar.YEAR), focusDay.get(Calendar.MONTH), focusDay.get(Calendar.DAY_OF_MONTH)));
    }

    private void refreshEventListView(ArrayList<Event> list) {
        Log.d(TAG, "refreshEventListView: refreshing recyclerview");
        RecyclerView eventListView = findViewById(R.id.event_list_view);
        adapter = new EventListAdapter(this, list);
        eventListView.setAdapter(adapter);
        eventListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
