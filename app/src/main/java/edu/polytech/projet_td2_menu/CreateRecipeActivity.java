package edu.polytech.projet_td2_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class CreateRecipeActivity extends AppCompatActivity implements NavigationBarInterface {

    private NavigationBarInterfaceImplementation implementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        findViewById(R.id.button_delete).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ListRecipeActivity.class)));
        findViewById(R.id.button_save).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ListRecipeActivity.class)));
        findViewById(R.id.image_recette).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), PhotoLoadActivity.class)));
        findViewById(R.id.back_button).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ListRecipeActivity.class)));

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