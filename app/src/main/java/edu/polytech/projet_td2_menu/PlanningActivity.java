package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PlanningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
    }

    public static class Planning_container extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_planning_container);
        }
    }
}