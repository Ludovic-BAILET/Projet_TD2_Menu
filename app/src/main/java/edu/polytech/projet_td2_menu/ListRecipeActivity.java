package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterRecipe;
import edu.polytech.projet_td2_menu.network.ApiTask;

public class ListRecipeActivity extends AppCompatActivity {

    private ViewAdapterRecipe recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerViewListRecipe = findViewById(R.id.list_recipes);
        recipeAdapter = new ViewAdapterRecipe();
        recyclerViewListRecipe.setAdapter(recipeAdapter);

        ApiTask apiTask = new ApiTask();

        setContentView(R.layout.activity_list_recipe);

        apiTask.execute();
    }
}