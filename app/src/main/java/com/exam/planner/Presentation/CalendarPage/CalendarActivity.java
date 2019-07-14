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

import com.exam.planner.Logic.Events.DateOutOfBoundsException;
import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Events.TimeOutOfBoundsException;
import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;
import com.exam.planner.Presentation.Settings.SettingsActivity;
import com.exam.planner.R;

import java.util.ArrayList;

import static com.exam.planner.Logic.Login.data.Repository.getInstance;

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";

    private ArrayList<Event> mEvents = new ArrayList<>();
    private EventListAdapter adapter;
    private Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Log.d(TAG, "onCreate: started");

        repo = getInstance(new UserPersistenceStub());

        final TabLayout navigationBar = findViewById(R.id.NavBar);

        final Intent settingsIntent = new Intent(this, SettingsActivity.class);

        final Button addEventButton = findViewById(R.id.add_event_button);

        initEventListView();

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event e = new Event();

                Intent editEventIntent = new Intent(v.getContext(), EventEditActivity.class);
                editEventIntent.putExtra("eventPos", -1);
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
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                int eventPos = data.getIntExtra("eventPos", -1);
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

                Event editEvent;
                if (eventPos >= 0)
                    editEvent = repo.getEvents().get(eventPos);
                else {
                    editEvent = new Event();
                    repo.addEvent(editEvent);
                }
                
                editEvent.editName(eventName);
                editEvent.editId(java.util.UUID.randomUUID().toString());
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

                adapter.notifyDataSetChanged();
            }else if (resultCode == 2){
                int eventPos = data.getIntExtra("eventPos", -1);
                if (eventPos >= 0) {
                    //mEvents.remove(eventPos)
                    repo.getUser().getPlanner().getEventsList().remove(eventPos);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }



    private void initEventListView() {
        Log.d(TAG, "initEventListView: init recyclerview");
        RecyclerView eventListView = findViewById(R.id.event_list_view);
        adapter = new EventListAdapter(this, repo.getEvents());
        eventListView.setAdapter(adapter);
        eventListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
