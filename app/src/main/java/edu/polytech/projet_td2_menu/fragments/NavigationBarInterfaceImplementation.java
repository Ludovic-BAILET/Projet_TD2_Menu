package edu.polytech.projet_td2_menu.fragments;


import android.app.Activity;
import android.content.Intent;
import android.view.View;

import edu.polytech.projet_td2_menu.ListRecipeActivity;
import edu.polytech.projet_td2_menu.ListeCourseActivity;
import edu.polytech.projet_td2_menu.PlanningActivity;
import edu.polytech.projet_td2_menu.ProfilActivity;

public class NavigationBarInterfaceImplementation implements NavigationBarInterface {

    private final Activity activity;

    public NavigationBarInterfaceImplementation(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onButtonPlanningClicked(View v) {
        v.setOnClickListener(click -> activity.startActivity(new Intent(activity.getApplicationContext(), PlanningActivity.class)));
    }

    @Override
    public void onButtonProfilClicked(View v) {
        v.setOnClickListener(click -> activity.startActivity(new Intent(activity.getApplicationContext(), ProfilActivity.class)));
    }

    @Override
    public void onButtonMesCoursesClicked(View v) {
        v.setOnClickListener(click -> activity.startActivity(new Intent(activity.getApplicationContext(), ListeCourseActivity.class)));
    }

    @Override
    public void onButtonRecettesClicked(View v) {
        v.setOnClickListener(click -> activity.startActivity(new Intent(activity.getApplicationContext(), ListRecipeActivity.class)));
    }


}
