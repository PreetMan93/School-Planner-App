package com.exam.planner.Logic.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.exam.planner.Presentation.CalendarPage.CalendarActivity;
import com.exam.planner.R;

public class SettingsAccountActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_account);

        button = findViewById(R.id.AccountSaveButton);
        final Intent intent = new Intent(this, CalendarActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
