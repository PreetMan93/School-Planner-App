package com.exam.planner.Presentation.Settings;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.exam.planner.Logic.Login.data.DataSource;
import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Logic.Login.data.model.LoggedInUser;
import com.exam.planner.Presentation.CalendarPage.CalendarActivity;
import com.exam.planner.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final TabLayout navigationBar = findViewById(R.id.NavBar);
        final Intent homeIntent = new Intent(this, CalendarActivity.class);
        navigationBar.getTabAt(1).select();

        navigationBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().equals("Home")){
                    startActivity(homeIntent);
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
}
