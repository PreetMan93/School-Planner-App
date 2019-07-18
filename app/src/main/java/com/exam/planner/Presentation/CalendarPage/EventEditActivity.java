package com.exam.planner.Presentation.CalendarPage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.planner.Logic.Events.CalendarFormatter;
import com.exam.planner.Logic.Events.CalendarInvalidFormatException;
import com.exam.planner.Logic.Events.DateOutOfBoundsException;
import com.exam.planner.Logic.Events.DateTime;
import com.exam.planner.Logic.Events.DateTimeValidationException;
import com.exam.planner.Logic.Events.TimeOutOfBoundsException;
import com.exam.planner.R;

import java.util.Calendar;

public class EventEditActivity extends AppCompatActivity {
    private static final String TAG = "EventEditActivity";

    private String eventName, eventId, eventCopyId;
    private int startYear, startMonth, startDay, startHour, startMinute;
    private int endYear, endMonth, endDay, endHour, endMinute;
    private int repeatYear, repeatMonth, repeatDay;

    private EditText eventNameField, eventStartDateField, eventStartTimeField, eventEndDateField, eventEndTimeField, eventRepeatField;
    private CheckBox sundayBox, mondayBox, tuesdayBox, wednesdayBox, thursdayBox, fridayBox, saturdayBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: started");

        eventNameField = findViewById(R.id.event_edit_name_field);
        eventStartDateField = findViewById(R.id.event_edit_start_date_field);
        eventStartTimeField = findViewById(R.id.event_edit_start_time_field);
        eventEndDateField = findViewById(R.id.event_edit_end_date_field);
        eventEndTimeField = findViewById(R.id.event_edit_end_time_field);
        eventRepeatField = findViewById(R.id.event_edit_repeat_date_field);

        sundayBox = findViewById(R.id.sunday_checkbox);
        mondayBox = findViewById(R.id.monday_checkbox);
        tuesdayBox = findViewById(R.id.tuesday_checkbox);
        wednesdayBox = findViewById(R.id.wednesday_checkbox);
        thursdayBox = findViewById(R.id.thursday_checkbox);
        fridayBox = findViewById(R.id.friday_checkbox);
        saturdayBox = findViewById(R.id.saturday_checkbox);

        eventId = getIntent().getStringExtra("eventId");

        if (getIntent().hasExtra("eventCopyId")){
            eventCopyId = getIntent().getStringExtra("eventCopyId");
        }else
            eventCopyId = null;

        if (getIntent().hasExtra("eventName")){
            eventName = getIntent().getStringExtra("eventName");
            eventNameField.setText(eventName);
        }else
            eventName = "";

        if (getIntent().hasExtra("eventStartYear"))
            startYear = getIntent().getIntExtra("eventStartYear", 1900);
        else
            startYear = 1900;

        if (getIntent().hasExtra("eventStartMonth"))
            startMonth = getIntent().getIntExtra("eventStartMonth", 1);
        else
            startMonth = 1;

        if (getIntent().hasExtra("eventStartDay"))
            startDay = getIntent().getIntExtra("eventStartDay", 1);
        else
            startDay = 1;

        if (getIntent().hasExtra("eventStartHour"))
            startHour = getIntent().getIntExtra("eventStartHour", 0);
        else
            startHour = 0;

        if (getIntent().hasExtra("eventStartMinute"))
            startMinute = getIntent().getIntExtra("eventStartMinute", 0);
        else
            startMinute = 0;


        if (getIntent().hasExtra("eventEndYear"))
            endYear = getIntent().getIntExtra("eventEndYear", 1900);
        else
            endYear = 1900;

        if (getIntent().hasExtra("eventEndMonth"))
            endMonth = getIntent().getIntExtra("eventEndMonth", 1);
        else
            endMonth = 1;

        if (getIntent().hasExtra("eventEndDay"))
            endDay = getIntent().getIntExtra("eventEndDay", 1);
        else
            endDay = 1;

        if (getIntent().hasExtra("eventEndHour"))
            endHour = getIntent().getIntExtra("eventEndHour", 0);
        else
            endHour = 0;

        if (getIntent().hasExtra("eventEndMinute"))
            endMinute = getIntent().getIntExtra("eventEndMinute", 0);
        else
            endMinute = 0;

        eventStartDateField.setText(CalendarFormatter.dateToString(startYear, startMonth, startDay));
        eventStartTimeField.setText(CalendarFormatter.timeToString(startHour, startMinute));
        eventEndDateField.setText(CalendarFormatter.dateToString(endYear, endMonth, endDay));
        eventEndTimeField.setText(CalendarFormatter.timeToString(endHour, endMinute));

        Calendar week = Calendar.getInstance();
        week.set(Calendar.YEAR, startYear);
        week.set(Calendar.MONDAY, startMonth);
        week.set(Calendar.DAY_OF_MONTH, startDay);
        week.add(Calendar.DAY_OF_MONTH, 6);

        repeatYear = week.get(Calendar.YEAR);
        repeatMonth = week.get(Calendar.MONTH);
        repeatDay = week.get(Calendar.DAY_OF_MONTH);

        eventRepeatField.setText(CalendarFormatter.dateToString(repeatYear, repeatMonth, repeatDay));


        eventNameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    eventName = eventNameField.getText().toString();
                }
            }
        });

        eventNameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    eventName = eventNameField.getText().toString();
                }
                return false;
            }
        });

        eventStartDateField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    updateStartDateField();
                }
            }
        });

        eventStartDateField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    updateStartDateField();
                    return false;
                }
                return false;
            }
        });

        eventStartTimeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    updateStartTimeField();
                }
            }
        });

        eventStartTimeField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    updateStartTimeField();
                    return false;
                }
                return false;
            }
        });

        eventEndDateField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    updateEndDateField();
                }
            }
        });

        eventEndDateField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    updateEndDateField();
                    return false;
                }
                return false;
            }
        });

        eventEndTimeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    updateEndTimeField();
                }
            }
        });

        eventEndTimeField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    updateEndTimeField();
                    return false;
                }
                return false;
            }
        });

        eventRepeatField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    updateRepeatField();
                }
            }
        });

        eventRepeatField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    updateRepeatField();
                }
                return false;
            }
        });

        Button deleteButton = findViewById(R.id.event_edit_delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent deleteIntent = getIntent();
                deleteIntent.putExtra("eventId", eventId);
                //packaged info includes start day so that the CalendarView can focus on the right day
                deleteIntent.putExtra("eventStartYear", startYear);
                deleteIntent.putExtra("eventStartMonth", startMonth);
                deleteIntent.putExtra("eventStartDay", startDay);
                if (eventCopyId != null){
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Delete Copied Events")
                            .setMessage("Delete all copies of this event?")
                            .setIcon(android.R.drawable.ic_delete)
                            .setPositiveButton("Delete All", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setResult(3, deleteIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton("Delete Single", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setResult(2, deleteIntent);
                                    finish();
                                }
                            })
                            .setNeutralButton("Cancel", null).show();
                }else{
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Delete Event")
                            .setMessage("Are you sure you would like to delete this event?")
                            .setIcon(android.R.drawable.ic_delete)
                            .setPositiveButton("Delete Event", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setResult(3, deleteIntent);
                                    finish();
                                }
                            })
                            .setNeutralButton("Cancel", null).show();
                }
            }
        });

        Button saveButton = findViewById(R.id.event_edit_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] repeatArray = {sundayBox.isChecked(), mondayBox.isChecked(), tuesdayBox.isChecked(), wednesdayBox.isChecked(), thursdayBox.isChecked(), fridayBox.isChecked(), saturdayBox.isChecked()};
                boolean doRepeat = false;
                for (boolean b: repeatArray)
                    doRepeat = doRepeat || b;
                try {
                    DateTime.validateEndAfterStart(startYear, startMonth, startDay, startHour, startMinute, endYear, endMonth, endDay, endHour, endMinute);
                    if (doRepeat)
                        DateTime.validateEndAfterStart(startYear, startMonth, startDay, startHour, startMinute, repeatYear, repeatMonth, repeatDay, 0, 0);
                    
                    Intent returnIntent = getIntent();

                    returnIntent.putExtra("eventId", eventId);
                    returnIntent.putExtra("eventName", eventNameField.getText().toString());

                    returnIntent.putExtra("eventStartYear", startYear);
                    returnIntent.putExtra("eventStartMonth", startMonth);
                    returnIntent.putExtra("eventStartDay", startDay);
                    returnIntent.putExtra("eventStartHour", startHour);
                    returnIntent.putExtra("eventStartMinute", startMinute);

                    returnIntent.putExtra("eventEndYear", endYear);
                    returnIntent.putExtra("eventEndMonth", endMonth);
                    returnIntent.putExtra("eventEndDay", endDay);
                    returnIntent.putExtra("eventEndHour", endHour);
                    returnIntent.putExtra("eventEndMinute", endMinute);

                    returnIntent.putExtra("eventRepeatList", repeatArray);

                    returnIntent.putExtra("eventRepeatYear", repeatYear);
                    returnIntent.putExtra("eventRepeatMonth", repeatMonth);
                    returnIntent.putExtra("eventRepeatDay", repeatDay);

                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                    Log.d(TAG, "onClick: Returning to CalendarActivity");
                } catch (DateTimeValidationException e) {
                    if (doRepeat)
                        Toast.makeText(EventEditActivity.this, "End and repeat dates must be later than the start date", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(EventEditActivity.this, "Events must start before they end", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateStartDateField() {
        Log.d(TAG, "updateStartDateField() called");
        try {
            int[] date = CalendarFormatter.dateToInt(eventStartDateField.getText().toString());
            DateTime.validateDate(date[0], date[1], date[2]);
            startYear = date[0];
            startMonth = date[1];
            startDay = date[2];
            Toast.makeText(EventEditActivity.this, "Valid date", Toast.LENGTH_SHORT).show();
        } catch (CalendarInvalidFormatException e) {
            eventStartDateField.setText(CalendarFormatter.dateToString(startYear, startMonth, startDay));
            Toast.makeText(EventEditActivity.this, "Date must be formatted YYYY/MM/DD", Toast.LENGTH_SHORT).show();
        } catch (DateOutOfBoundsException e) {
            eventStartDateField.setText(CalendarFormatter.dateToString(startYear, startMonth, startDay));
            Toast.makeText(EventEditActivity.this, "Please enter a valid date after January 1, 1900", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateEndDateField() {
        Log.d(TAG, "updateEndDateField() called");
        try {
            int[] date = CalendarFormatter.dateToInt(eventEndDateField.getText().toString());
            DateTime.validateDate(date[0], date[1], date[2]);
            endYear = date[0];
            endMonth = date[1];
            endDay = date[2];
            Toast.makeText(EventEditActivity.this, "Valid date", Toast.LENGTH_SHORT).show();
        } catch (CalendarInvalidFormatException e) {
            eventEndDateField.setText(CalendarFormatter.dateToString(endYear, endMonth, endDay));
            Toast.makeText(EventEditActivity.this, "Date must be formatted YYYY/MM/DD", Toast.LENGTH_SHORT).show();
        } catch (DateOutOfBoundsException e) {
            eventEndDateField.setText(CalendarFormatter.dateToString(endYear, endMonth, endDay));
            Toast.makeText(EventEditActivity.this, "Please enter a valid date after January 1, 1900", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStartTimeField() {
        Log.d(TAG, "updateStartTimeField() called");
        try {
            int[] time = CalendarFormatter.timeToInt(eventStartTimeField.getText().toString());
            DateTime.validateTime(time[0], time[1]);
            startHour = time[0];
            startMinute = time[1];
            Toast.makeText(EventEditActivity.this, "Valid time", Toast.LENGTH_SHORT).show();
        } catch (CalendarInvalidFormatException e) {
            eventStartTimeField.setText(CalendarFormatter.timeToString(startHour, startMinute));
            Toast.makeText(EventEditActivity.this, "Time must be formatted HH:MM", Toast.LENGTH_SHORT).show();
        } catch (TimeOutOfBoundsException e){
            eventStartTimeField.setText(CalendarFormatter.timeToString(startHour, startMinute));
            Toast.makeText(EventEditActivity.this, "Please enter an hour from 0 and 23 and a minute from 0 to 59", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateEndTimeField() {
        Log.d(TAG, "updateEndTimeField() called");
        try {
            int[] time = CalendarFormatter.timeToInt(eventEndTimeField.getText().toString());
            DateTime.validateTime(time[0], time[1]);
            endHour = time[0];
            endMinute = time[1];
            Toast.makeText(EventEditActivity.this, "Valid time", Toast.LENGTH_SHORT).show();
        } catch (CalendarInvalidFormatException e) {
            eventEndTimeField.setText(CalendarFormatter.timeToString(endHour, endMinute));
            Toast.makeText(EventEditActivity.this, "Time must be formatted HH:MM", Toast.LENGTH_SHORT).show();
        } catch (TimeOutOfBoundsException e){
            eventEndTimeField.setText(CalendarFormatter.timeToString(endHour, endMinute));
            Toast.makeText(EventEditActivity.this, "Please enter an hour from 0 and 23 and a minute from 0 to 59", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateRepeatField() {
        Log.d(TAG, "updateRepeatField() called");
        try {
            int[] date = CalendarFormatter.dateToInt(eventRepeatField.getText().toString());
            DateTime.validateDate(date[0], date[1], date[2]);
            DateTime.validateEndAfterStart(startYear, startMonth, startDay, startMinute, startHour, date[0], date[1], date[2], 0, 0);
            repeatYear = date[0];
            repeatMonth = date[1];
            repeatDay = date[2];
            Toast.makeText(EventEditActivity.this, "Valid date", Toast.LENGTH_SHORT).show();
        } catch (CalendarInvalidFormatException e) {
            eventRepeatField.setText(CalendarFormatter.dateToString(repeatYear, repeatMonth, repeatDay));
            Toast.makeText(EventEditActivity.this, "Date must be formatted YYYY/MM/DD", Toast.LENGTH_SHORT).show();
        } catch (DateOutOfBoundsException e) {
            eventRepeatField.setText(CalendarFormatter.dateToString(repeatYear, repeatMonth, repeatDay));
            Toast.makeText(EventEditActivity.this, "Please enter a valid date after January 1, 1900", Toast.LENGTH_SHORT).show();
        } catch (DateTimeValidationException e) {
            eventRepeatField.setText(CalendarFormatter.dateToString(repeatYear, repeatMonth, repeatDay));
            Toast.makeText(EventEditActivity.this, "Repetition date must be after Event date", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: EventEditActivity is gone!");
    }
}
