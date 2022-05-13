package edu.polytech.projet_td2_menu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.polytech.projet_td2_menu.MVC.NotificationsCenterActivity;
import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class PlanningActivity extends AppCompatActivity implements NavigationBarInterface {

    private static final String TAG = "PlanningActivity";
    private TextView theDate;
    private Calendar calendar;
    private ImageView imageCalendar;
    private NavigationBarInterfaceImplementation implementation;
    private ImageView addToAgenda;

    private final int WRITE_CALENDAR_PERMISSION = 101;
    private final int READ_CALENDAR_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        theDate = findViewById(R.id.date);
        imageCalendar = findViewById(R.id.calendar);
        addToAgenda = findViewById(R.id.addToAgenda);
        implementation = new NavigationBarInterfaceImplementation(this);


        Intent incomingIntent = getIntent();
        String currentDate = incomingIntent.getStringExtra("date");
        if (currentDate == null) {
            currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            theDate.setText(currentDate);

        } else {
            theDate.setText(currentDate);
        }

        String[] dateInt = currentDate.split("/");
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(dateInt[2]));
        calendar.set(Calendar.MONTH, Integer.valueOf(dateInt[1]));
        calendar.set(Calendar.DATE, Integer.valueOf(dateInt[0]));

        Log.d(TAG, "onCreate: " + calendar.getTimeInMillis());

        imageCalendar.setOnClickListener(view -> startActivity(new Intent(PlanningActivity.this, CalendarActivity.class)));

        findViewById(R.id.bell).setOnClickListener(click -> startActivity(new Intent(this, NotificationsCenterActivity.class)));

        addToAgenda.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_DENIED) {
                    //permission not granted
                    String[] permissions = {Manifest.permission.WRITE_CALENDAR};
                    //show popup for runtime permission
                    requestPermissions(permissions, WRITE_CALENDAR_PERMISSION);
                } else if (checkSelfPermission(Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_DENIED) {
                    //permission not granted
                    String[] permissions = {Manifest.permission.READ_CALENDAR};
                    //show popup for runtime permission
                    requestPermissions(permissions, READ_CALENDAR_PERMISSION);

                } else {
                    //permission already granted
                    putEventToCalendar();
                }
            } else {
                //system os is less than marsmallow
                putEventToCalendar();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

    }


    public void putEventToCalendar() {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, "liste des courses du " + theDate.getText());
        //TODO reformuler la description pour l'agenda
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "La liste des courses à acheter pour le " + theDate.getText());

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendar.getTimeInMillis());
        intent.putExtra(CalendarContract.Events.DURATION, 100000);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Biot");
        intent.putExtra(CalendarContract.Events.CALENDAR_ID, 1);
        intent.putExtra(CalendarContract.Events.ALL_DAY, true);

        startActivity(intent);

    }

//    public void putEventToCalendar(){
//        long millis = 1000;
//        String evtext = "test";
//
//        Intent intent = new Intent(Intent.ACTION_INSERT)
//                .setData(CalendarContract.Events.CONTENT_URI)
//                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, millis)
//                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, millis + 60 * 60 * 1000)
//                .putExtra(CalendarContract.Events.TITLE, evtext)
//                .putExtra(CalendarContract.Events.DESCRIPTION, evtext)
//                .putExtra(CalendarContract.Events.HAS_ALARM, true)
//                .putExtra(CalendarContract.Reminders.EVENT_ID, CalendarContract.Events._ID)
//                .putExtra(CalendarContract.Events.ALLOWED_REMINDERS, "METHOD_DEFAULT")
//                .putExtra(CalendarContract.Reminders.MINUTES, 1)
//                .putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
//                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
//
//        startActivity(intent);
//
//    }


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