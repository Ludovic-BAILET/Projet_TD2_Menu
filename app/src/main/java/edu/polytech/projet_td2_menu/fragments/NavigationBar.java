package edu.polytech.projet_td2_menu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import edu.polytech.projet_td2_menu.ListRecipeActivity;
import edu.polytech.projet_td2_menu.PlanningActivity;
import edu.polytech.projet_td2_menu.R;

public class NavigationBar extends Fragment {
    public NavigationBar() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.navigation_bar, container, false);

        //Listener de Planning
        layout.findViewById(R.id.planning).setOnClickListener(click -> {
            startActivity(new Intent(getContext(), PlanningActivity.class));
        });

        //Listener de Recettes
        layout.findViewById(R.id.recettes).setOnClickListener(click -> {
            startActivity(new Intent(getContext(), ListRecipeActivity.class));
        });

        //Listener de Mes Courses
        layout.findViewById(R.id.mes_courses).setOnClickListener(click -> {
            startActivity(new Intent(getContext(), PlanningActivity.class));
        });

        //Listener de Profil
        layout.findViewById(R.id.profil).setOnClickListener(click -> {
            startActivity(new Intent(getContext(), PlanningActivity.class));
        });

        return layout;
    }
}
