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

    private static final int ACTIVITY_PLANNING = 1;
    private static final int ACTIVITY_LISTE = 2;

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



            int callingActivity = getIntent().getIntExtra("calling-activity", 1);

            Intent intent = new Intent(CalendarActivity.this,PlanningActivity.class );

            switch (callingActivity) {
                case ACTIVITY_PLANNING:
                    intent = new Intent(CalendarActivity.this,PlanningActivity.class );
                    break;
                case ACTIVITY_LISTE:
                    intent = new Intent(CalendarActivity.this,ListeCourseActivity.class );
                    break;
            }



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