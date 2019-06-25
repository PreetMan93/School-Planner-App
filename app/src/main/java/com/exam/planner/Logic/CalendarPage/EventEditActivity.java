package com.exam.planner.Logic.CalendarPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.planner.R;

public class EventEditActivity extends AppCompatActivity {

    private int eventPos;
    private String eventName;
    private String eventStartDate;
    private String eventStartTime;
    private String eventEndDate;
    private String eventEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText eventNameField = findViewById(R.id.event_edit_name_field);
        final EditText eventStartDateField = findViewById(R.id.event_edit_start_date_field);
        final EditText eventStartTimeField = findViewById(R.id.event_edit_start_time_field);
        final EditText eventEndDateField = findViewById(R.id.event_edit_end_date_field);
        final EditText eventEndTimeField = findViewById(R.id.event_edit_end_time_field);

        final CheckBox sundayBox = findViewById(R.id.sunday_checkbox);
        final CheckBox mondayBox = findViewById(R.id.monday_checkbox);
        final CheckBox tuesdayBox = findViewById(R.id.tuesday_checkbox);
        final CheckBox wednesdayBox = findViewById(R.id.wednesday_checkbox);
        final CheckBox thursdayBox = findViewById(R.id.thursday_checkbox);
        final CheckBox fridayBox = findViewById(R.id.friday_checkbox);
        final CheckBox saturdayBox = findViewById(R.id.saturday_checkbox);

        eventPos = getIntent().getIntExtra("eventPos", -1);

        if (getIntent().hasExtra("eventName")){
            eventName = getIntent().getStringExtra("eventName");
            eventNameField.setText(eventName);
        }else
            eventName = "";

        if (getIntent().hasExtra("eventStartDate")){
            eventStartDate = getIntent().getStringExtra("eventStartDate");
            eventStartDateField.setText(eventStartDate);
        }else
            eventStartDate = "";

        if (getIntent().hasExtra("eventStartTime")){
            eventStartTime = getIntent().getStringExtra("eventStartTime");
            eventStartTimeField.setText(eventStartTime);
        }else
            eventStartTime = "";

        if (getIntent().hasExtra("eventEndDate")){
            eventEndDate = getIntent().getStringExtra("eventEndDate");
            eventEndDateField.setText(eventEndDate);
        }else
            eventEndDate = "";

        if (getIntent().hasExtra("eventEndTime")){
            eventEndTime = getIntent().getStringExtra("eventEndTime");
            eventEndTimeField.setText(eventEndTime);
        }else
            eventEndTime = "";

        eventNameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    eventName = eventNameField.getText().toString();
                }
            }
        });

        eventStartDateField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    try{
                        Event.validateDate(eventStartDateField.getText().toString());
                        eventStartDate = eventStartDateField.getText().toString();
                        Toast.makeText(EventEditActivity.this, "Valid date", Toast.LENGTH_SHORT).show();
                    } catch (EventDateInvalidFormatException e){
                        eventStartDateField.setText(eventStartDate);
                        Toast.makeText(EventEditActivity.this, "Date must be formatted YYYY/MM/DD", Toast.LENGTH_SHORT).show();
                    } catch (EventDateOutOfBoundsException e){
                        eventStartDateField.setText(eventStartDate);
                        Toast.makeText(EventEditActivity.this, "Please enter a valid date after January 1, 1900", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        eventStartTimeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    try{
                        Event.validateTime(eventStartTimeField.getText().toString());
                        eventStartTime = eventStartTimeField.getText().toString();
                        Toast.makeText(EventEditActivity.this, "Valid time", Toast.LENGTH_SHORT).show();
                    } catch (EventTimeInvalidFormatException e){
                        eventStartTimeField.setText(eventStartTime);
                        Toast.makeText(EventEditActivity.this, "Time must be formatted HH:MM", Toast.LENGTH_SHORT).show();
                    } catch (EventTimeOutOfBoundsException e){
                        eventStartTimeField.setText(eventStartTime);
                        Toast.makeText(EventEditActivity.this, "Please enter an hour from 0 and 23 and a minute from 0 to 59", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        eventEndDateField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    try{
                        Event.validateDate(eventEndDateField.getText().toString());
                        eventEndDate = eventEndDateField.getText().toString();
                        Toast.makeText(EventEditActivity.this, "Valid date", Toast.LENGTH_SHORT).show();
                    } catch (EventDateInvalidFormatException e){
                        eventEndDateField.setText(eventEndDate);
                        Toast.makeText(EventEditActivity.this, "Date must be formatted YYYY/MM/DD", Toast.LENGTH_SHORT).show();
                    } catch (EventDateOutOfBoundsException e){
                        eventEndDateField.setText(eventEndDate);
                        Toast.makeText(EventEditActivity.this, "Please enter a valid date after January 1, 1900", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        eventEndTimeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    try{
                        Event.validateTime(eventEndTimeField.getText().toString());
                        eventEndTime = eventEndTimeField.getText().toString();
                        Toast.makeText(EventEditActivity.this, "Valid time", Toast.LENGTH_SHORT).show();
                    } catch (EventTimeInvalidFormatException e){
                        eventEndTimeField.setText(eventEndTime);
                        Toast.makeText(EventEditActivity.this, "Time must be formatted HH:MM", Toast.LENGTH_SHORT).show();
                    } catch (EventTimeOutOfBoundsException e){
                        eventEndTimeField.setText(eventEndTime);
                        Toast.makeText(EventEditActivity.this, "Please enter an hour from 0 and 23 and a minute from 0 to 59", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button saveButton = findViewById(R.id.event_edit_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("eventPos", eventPos);
                returnIntent.putExtra("eventName", eventName);
                returnIntent.putExtra("eventStartDate", eventStartDate);
                returnIntent.putExtra("eventStartTime", eventStartTime);
                returnIntent.putExtra("eventEndDate", eventEndDate);
                returnIntent.putExtra("eventEndTime", eventEndTime);
                boolean[] repeatArray = {sundayBox.isChecked(), mondayBox.isChecked(), tuesdayBox.isChecked(), wednesdayBox.isChecked(), thursdayBox.isChecked(), fridayBox.isChecked(), saturdayBox.isChecked()};
                returnIntent.putExtra("eventRepeatList", repeatArray);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}
