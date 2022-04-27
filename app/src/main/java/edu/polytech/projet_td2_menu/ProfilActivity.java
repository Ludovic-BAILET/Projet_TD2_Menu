package edu.polytech.projet_td2_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class ProfilActivity extends AppCompatActivity implements NavigationBarInterface {

    private Button buttonTMP;
    private NavigationBarInterfaceImplementation implementation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        implementation = new NavigationBarInterfaceImplementation(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

        buttonTMP = (Button) findViewById(R.id.tmp_camera);
        buttonTMP.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilActivity.this,PhotoLoadActivity.class);
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