package edu.polytech.projet_td2_menu;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterAdvancedFilter;
import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class FilterActivity extends AppCompatActivity implements NavigationBarInterface {

    private ViewAdapterAdvancedFilter adapter;
    private NavigationBarInterfaceImplementation implementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        implementation = new NavigationBarInterfaceImplementation(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

        adapter = new ViewAdapterAdvancedFilter(getApplicationContext());
        ViewGroup layout = findViewById(R.id.view_filters);
        ((ListView) layout.findViewById(R.id.list_filters)).setAdapter(adapter);

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