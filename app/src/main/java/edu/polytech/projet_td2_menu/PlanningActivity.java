package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import edu.polytech.projet_td2_menu.MVC.NotificationsCenterActivity;
import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class PlanningActivity extends AppCompatActivity implements NavigationBarInterface {

    private static final String  TAG = "PlanningActivity";
    private TextView theDate;
    private ImageView imageCalendar;
    private NavigationBarInterfaceImplementation implementation;
    private ImageView addToAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        theDate = findViewById(R.id.date);
        imageCalendar = findViewById(R.id.calendar);
        addToAgenda = findViewById(R.id.addToAgenda);
        implementation = new NavigationBarInterfaceImplementation(this);


        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        imageCalendar.setOnClickListener(view -> {
            startActivity(new Intent(PlanningActivity.this,CalendarActivity.class));
        });

        findViewById(R.id.bell).setOnClickListener(click -> {
            startActivity(new Intent(this, NotificationsCenterActivity.class));
        });


        addToAgenda.setOnClickListener(view -> {
            ContentResolver cr  = getContentResolver();
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
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

    }

    @Override
    public void onButtonPlanningClicked(View v) {
        implementation.onButtonPlanningClicked(v);
    }

    @Override
    public void onButtonProfilClicked(View v) {
        implementation.onButtonProfilClicked(v);
    }

    @Override
    public void onButtonMesCoursesClicked(View v) {
        implementation.onButtonMesCoursesClicked(v);
    }

    @Override
    public void onButtonRecettesClicked(View v) {
        implementation.onButtonRecettesClicked(v);
    }
}