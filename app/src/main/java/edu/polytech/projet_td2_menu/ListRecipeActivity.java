package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterRecipe;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;
import edu.polytech.projet_td2_menu.network.ApiTask;

public class ListRecipeActivity extends AppCompatActivity {

    private ViewAdapterRecipe adapterRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Recipe> recipeList = getRecipeListFromApi();
        adapterRecipe = new ViewAdapterRecipe(getApplicationContext(), recipeList);

        ConstraintLayout constraintLayout = findViewById(R.id.activity_list_recipe);

        ((ListView) constraintLayout.findViewById(R.id.list_recipes)).setAdapter(adapterRecipe);

        setContentView(R.layout.activity_list_recipe);
    }

    private List<Recipe> getRecipeListFromApi() {
        ApiTask apiTask = new ApiTask();
        apiTask.execute();
        while (ApiTask.recipeList == null){

        }
        return ApiTask.recipeList;
    }
}