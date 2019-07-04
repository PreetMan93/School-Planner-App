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

import com.exam.planner.DSO.Events.Event;
import com.exam.planner.Presentation.Settings.SettingsActivity;
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

        final Button settingsButton = findViewById(R.id.SettingsButton);
        final Intent settingsIntent = new Intent(this, SettingsActivity.class);

        final Button addEventButton = findViewById(R.id.add_event_button);

        final TabLayout navigationBar = findViewById(R.id.NavBar);

        populateEvents();
        initEventListView();

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = mEvents.size();
                Event newEvent = new Event();

                Intent editEventIntent = new Intent(v.getContext(), EventEditActivity.class);
                editEventIntent.putExtra("eventPos", -1);
                editEventIntent.putExtra("eventName", newEvent.getName());
                editEventIntent.putExtra("eventStartDate", newEvent.getStartDateString());
                editEventIntent.putExtra("eventStartTime", newEvent.getStartTimeString());
                editEventIntent.putExtra("eventEndDate", newEvent.getEndDateString());
                editEventIntent.putExtra("eventEndTime", newEvent.getEndTimeString());

                ((Activity)v.getContext()).startActivityForResult(editEventIntent, 1);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settingsIntent);
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
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                int eventPos = data.getIntExtra("eventPos", -1);
                String eventName = data.getStringExtra("eventName");
                String eventStartDate = data.getStringExtra("eventStartDate");
                String eventStartTime = data.getStringExtra("eventStartTime");
                String eventEndDate = data.getStringExtra("eventEndDate");
                String eventEndTime = data.getStringExtra("eventEndTime");

                Event editEvent;
                if (eventPos >= 0)
                    editEvent = mEvents.get(eventPos);
                else {
                    editEvent = new Event();
                    mEvents.add(editEvent);
                }

                editEvent.editName(eventName);
                editEvent.editStartDate(eventStartDate, eventStartTime);
                editEvent.editEndDate(eventEndDate, eventEndTime);

                adapter.notifyDataSetChanged();
            }else if (resultCode == 2){
                int eventPos = data.getIntExtra("eventPos", -1);
                if (eventPos >= 0) {
                    mEvents.remove(eventPos);

                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void populateEvents() {
        Log.d(TAG, "populateEvents: preparing events");

        Event event1 = new Event();
        event1.editName("Homework 1");
        event1.editStartDate(2022, 12, 13, 14, 00);

        Event event2 = new Event();
        event2.editName("Test 1");
        event2.editStartDate(2022, 12, 15, 14, 05);

        mEvents.add(event1);
        mEvents.add(event2);
    }

    private void initEventListView() {
        Log.d(TAG, "initEventListView: init recyclerview");
        RecyclerView eventListView = findViewById(R.id.event_list_view);
        adapter = new EventListAdapter(this, mEvents);
        eventListView.setAdapter(adapter);
        eventListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
