package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PlanningActivity extends AppCompatActivity {

    private static final String  TAG = "PlanningActivity";
    private TextView theDate;
    private ImageView imageCalendar;

    private ImageView addToAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        theDate = findViewById(R.id.date);
        imageCalendar = findViewById(R.id.calendar);
        addToAgenda = findViewById(R.id.addToAgenda);


        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        imageCalendar.setOnClickListener(view -> {
            startActivity(new Intent(PlanningActivity.this,CalendarActivity.class));
        });

        findViewById(R.id.bell).setOnClickListener(click -> {
            startActivity(new Intent(this, NotificationsCenterActivity.class));
        });


        addToAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver cr  = getApplicationContext().getContentResolver();
                ContentValues cv = new ContentValues();
                cv.put(CalendarContract.Events.TITLE,"test title");
                cv.put(CalendarContract.Events.DESCRIPTION,"test description");
                cv.put(CalendarContract.Events.EVENT_LOCATION,"test event_location");
                cv.put(CalendarContract.Events.DTSTART, Calendar.getInstance().getTimeInMillis());
                cv.put(CalendarContract.Events.DTEND,Calendar.getInstance().getTimeInMillis()+60*60*1000);
                cv.put(CalendarContract.Events.CALENDAR_ID,1);
                cv.put(CalendarContract.Events.EVENT_TIMEZONE,Calendar.getInstance().getTimeZone().getID());

                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI,cv);

                Toast.makeText(getApplicationContext(),"Event is successfully added",Toast.LENGTH_SHORT).show();

            }
        });

    }





}