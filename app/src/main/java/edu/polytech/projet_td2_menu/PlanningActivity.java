package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanningActivity extends AppCompatActivity {

    private static final String  TAG = "PlanningActivity";
    private TextView theDate;
    private ImageView imageCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        theDate = findViewById(R.id.date);
        imageCalendar = findViewById(R.id.calendar);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        imageCalendar.setOnClickListener(view -> {
            startActivity(new Intent(PlanningActivity.this,CalendarActivity.class));
        });

        findViewById(R.id.bell).setOnClickListener(click -> {
            startActivity(new Intent(this, NotificationsCenterActivity.class));
        });
    }





}