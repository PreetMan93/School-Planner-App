package com.exam.planner.Presentation.CalendarPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.exam.planner.DSO.Events.Event;
import com.exam.planner.Logic.Settings.SettingsActivity;
import com.exam.planner.R;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";

    //variables
    private ArrayList<Event> mEvents = new ArrayList<>();

    private EventListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Log.d(TAG, "onCreate: started");

        final Button button = findViewById(R.id.SettingsButton);
        final Intent settingsIntent = new Intent(this, SettingsActivity.class);

        EventListAdapter adapter = new EventListAdapter(this, mEvents);

        populateEvents();
        initEventListView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settingsIntent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                int eventPos = data.getIntExtra("eventPos", -1);
                String eventName = data.getStringExtra("eventName");
                String eventStartDate = data.getStringExtra("eventStartDate");
                String eventStartTime = data.getStringExtra("eventStartTime");
                String eventEndDate = data.getStringExtra("eventEndDate");
                String eventEndTime = data.getStringExtra("eventEndTime");

                Event editEvent = mEvents.get(eventPos);
                editEvent.editName(eventName);
                editEvent.editStartDate(eventStartDate, eventStartTime);
                editEvent.editEndDate(eventEndDate, eventEndTime);

                adapter.notifyDataSetChanged();
            }
        }
    }

    private void populateEvents() {
        Log.d(TAG, "populateEvents: preparing events");

        Event event1 = new Event();
        event1.editName("Homework 1");
        event1.editStartDate(2022, 12, 13, 14, 25);

        Event event2 = new Event();
        event2.editName("Homework 2");
        event2.editStartDate(1991, 12, 13, 13, 25);

        Event event3 = new Event();
        event3.editName("Homework 3");
        event3.editStartDate(1991, 12, 13, 14, 59);

        mEvents.add(event1);
        mEvents.add(event2);
        mEvents.add(event3);
    }

    private void initEventListView() {
        Log.d(TAG, "initEventListView: init recyclerview");
        RecyclerView eventListView = findViewById(R.id.eventListView);
        adapter = new EventListAdapter(this, mEvents);
        eventListView.setAdapter(adapter);
        eventListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
