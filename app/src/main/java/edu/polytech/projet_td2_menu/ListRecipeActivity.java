package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;
import java.util.List;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterRecipe;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;
import edu.polytech.projet_td2_menu.network.ApiTask;


public class ListRecipeActivity extends AppCompatActivity implements NavigationBarInterface {

    private NavigationBarInterfaceImplementation implementation;

    private ViewAdapterRecipe adapterRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Recipe> recipeList = getRecipeListFromApi();
        adapterRecipe = new ViewAdapterRecipe(getApplicationContext(), recipeList);

        ConstraintLayout constraintLayout = findViewById(R.id.activity_list_recipe);

        ((ListView) constraintLayout.findViewById(R.id.list_recipes)).setAdapter(adapterRecipe);

        setContentView(R.layout.activity_list_recipe);

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

    private List<Recipe> getRecipeListFromApi() {
        ApiTask apiTask = new ApiTask();
        apiTask.execute();
        while (ApiTask.recipeList == null){

        }
        return ApiTask.recipeList;
    }
}