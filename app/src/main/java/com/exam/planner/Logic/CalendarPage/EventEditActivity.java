package com.exam.planner.Logic.CalendarPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exam.planner.R;

public class EventEditActivity extends AppCompatActivity {

    private int eventPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText eventNameField = findViewById(R.id.event_edit_name_field);
        eventPos = -1;

        if (getIntent().hasExtra("eventName")){
            String eventName = getIntent().getStringExtra("eventName");
            eventPos = getIntent().getIntExtra("eventPos", -1);
            eventNameField.setText(eventName);
        }

        Button saveButton = findViewById(R.id.event_edit_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                //returnIntent.putExtra("eventName", eventNameField.getText());
                returnIntent.putExtra("eventName", eventNameField.getText().toString());
                returnIntent.putExtra("eventPos", eventPos);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}
