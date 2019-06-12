package com.exam.planner.Logic.CalendarPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

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

        EventListAdapter adapter = new EventListAdapter(this, mEvents);

        populateEvents();
        initEventListView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                Event editEvent = mEvents.get(data.getIntExtra("eventPos", -1));
                editEvent.editName(data.getStringExtra("eventName"));
                Toast.makeText(this, editEvent.getName() + " edited", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void populateEvents() {
        Log.d(TAG, "populateEvents: preparing events");

        Event event1 = new Event();
        event1.editName("Homework 1");

        Event event2 = new Event();
        event2.editName("Homework 2");

        Event event3 = new Event();
        event3.editName("Homework 3");

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
