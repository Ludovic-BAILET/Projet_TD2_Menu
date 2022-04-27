package edu.polytech.projet_td2_menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class CalendarActivity extends AppCompatActivity implements NavigationBarInterface {

    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;
    private NavigationBarInterfaceImplementation implementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        implementation = new NavigationBarInterfaceImplementation(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            String date = i2 + "/" + (i1 + 1) + "/" + i;
            Log.d(TAG, "onSelectedDayChange: date " + date);

            Intent intent = new Intent(CalendarActivity.this, PlanningActivity.class);
            intent.putExtra("date", date);
            startActivity(intent);
        });
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