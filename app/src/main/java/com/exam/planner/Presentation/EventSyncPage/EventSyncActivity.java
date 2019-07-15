package com.exam.planner.Presentation.EventSyncPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Persistence.IUserPersistence;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;
import com.exam.planner.R;

public class EventSyncActivity extends AppCompatActivity {
    private static final String TAG = "EventSyncActivity";

    private EventSyncListAdapter eAdapter;
    private UserSyncListAdapter uAdapter;
    private IUserPersistence db;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_sync);
        Toolbar toolbar = findViewById(R.id.toolbar);
        button = findViewById(R.id.sync_events_save_btn);
        setSupportActionBar(toolbar);

        db = UserPersistenceStub.getInstance();

        RecyclerView eventSyncListView = findViewById(R.id.event_sync_list_view);
        RecyclerView userSyncListView = findViewById(R.id.user_sync_list_view);

        uAdapter = new UserSyncListAdapter(this, db.getUsers(), eAdapter, eventSyncListView);
        userSyncListView.setAdapter(uAdapter);
        userSyncListView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repository repo = Repository.getInstance(UserPersistenceStub.getInstance());
                for(Event e: uAdapter.getNewEvents()){
                    Log.d(TAG, "onClick: " + e.getId());
                    repo.addEvent(e.deepCopy());
                }
                finish();
            }
        });

    }
}
