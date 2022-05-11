package edu.polytech.projet_td2_menu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class ListeCourseActivity extends AppCompatActivity implements NavigationBarInterface {

    private static final String TAG = "ListeCourse";
    private NavigationBarInterfaceImplementation implementation;

    private TextView q1;
    private TextView q2;
    private TextView q3;
    private TextView q4;

    private TextView d1;
    private TextView d2;
    private TextView d3;
    private TextView d4;

    private Button week1;
    private Button week2;

    private ImageView calendar;
    private ImageView agenda;

    private int WRITE_CALENDAR_PERMISSION = 101;
    private int READ_CALENDAR_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_course);

        implementation = new NavigationBarInterfaceImplementation(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

        week1 = findViewById(R.id.week1);
        week2 = findViewById(R.id.week2);
        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        d3 = findViewById(R.id.d3);
        d4 = findViewById(R.id.d4);

        calendar = findViewById(R.id.calendar);
        agenda = findViewById(R.id.agenda);

        week1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q1.setText("500g");
                q2.setText("500g");
                q3.setText("6u");
                q4.setText("500g");
            }
        });


        week2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q1.setText("1kg");
                q2.setText("1kg");
                q3.setText("12u");
                q4.setText("1kg");
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListeCourseActivity.this,CalendarActivity.class);
                intent.putExtra("calling-activity",2);
                startActivity(intent);

            }
        });

        agenda.setOnClickListener(view -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_DENIED) {
                    //permission not granted
                    String[] permissions = {Manifest.permission.WRITE_CALENDAR};
                    //show popup for runtime permission
                    requestPermissions(permissions, WRITE_CALENDAR_PERMISSION);
                }
                else if(checkSelfPermission(Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_DENIED) {
                    //permission not granted
                    String[] permissions = {Manifest.permission.READ_CALENDAR};
                    //show popup for runtime permission
                    requestPermissions(permissions, READ_CALENDAR_PERMISSION);

                }else{
                    //permission already granted
                    putEventToCalendar();
                }
            }
            else{
                //system os is less than marsmallow
                putEventToCalendar();
            }
        });
    }

    public void putEventToCalendar(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, "liste des courses du " +  dateFormat.format(cal.getTime()));
        //TODO reformuler la description pour l'agenda
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "ingrédients :" + "\r\n" +
                d1.getText() + "  " + q1.getText() + "\r\n" +
                d2.getText() + "  " + q2.getText() + "\r\n" +
                d3.getText() + "  " + q3.getText() + "\r\n" +
                d4.getText() + "  " + q4.getText() + "\r\n"
                );

        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,"Biot");
        intent.putExtra(CalendarContract.Events.CALENDAR_ID,1);
        intent.putExtra(CalendarContract.Events.ALL_DAY,true );

        startActivity(intent);

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