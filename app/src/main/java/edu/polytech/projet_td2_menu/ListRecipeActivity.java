package edu.polytech.projet_td2_menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterRecipe;
import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;
import edu.polytech.projet_td2_menu.network.ApiTask;


public class ListRecipeActivity extends AppCompatActivity implements NavigationBarInterface, Observer {

    private final List<Recipe> recipeList = new ArrayList<>();
    private NavigationBarInterfaceImplementation implementation;
    private ViewAdapterRecipe adapterRecipe;
    private ApiTask apiTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_recipe);


        adapterRecipe = new ViewAdapterRecipe(getApplicationContext(), recipeList, this);

        apiTask = new ApiTask();


        apiTask.getModelRecipes().addObserver(this);
        findViewById(R.id.filter_button).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FilterActivity.class)));
        findViewById(R.id.button_add_recipe).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), CreateRecipeActivity.class)));

        ConstraintLayout constraintLayout = findViewById(R.id.activity_list_recipe);

        ((ListView) constraintLayout.findViewById(R.id.list_recipes)).setAdapter(adapterRecipe);


        implementation = new NavigationBarInterfaceImplementation(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

        callApi();
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

    private void callApi() {
        apiTask.execute();
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.d("object_List", "" + o);
        recipeList.clear();
        recipeList.addAll((List<Recipe>) o);
        runOnUiThread(() -> {
            adapterRecipe.notifyDataSetChanged();
        });

    }

}