package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class DetailRecipeActivity extends AppCompatActivity implements NavigationBarInterface {

    private NavigationBarInterfaceImplementation implementation;
    private boolean liked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

        findViewById(R.id.back_button).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ListRecipeActivity.class)));
        findViewById(R.id.image_favori).setOnClickListener(view -> {
            view.setBackgroundResource(liked ? R.drawable.heart_empty : R.drawable.heart_full);
            liked = !liked;
        });

        implementation = new NavigationBarInterfaceImplementation(this);
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