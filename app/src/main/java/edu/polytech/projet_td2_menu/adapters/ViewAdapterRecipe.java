package edu.polytech.projet_td2_menu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;

public class ViewAdapterRecipe extends BaseAdapter {

    private final LayoutInflater inflater;

    private final List<Recipe> recipeList;

    public ViewAdapterRecipe(Context context, List<Recipe> recipeList){

        this.inflater = LayoutInflater.from(context);
        this.recipeList = recipeList;
    }
    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Recipe getItem(int i) {
        return recipeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout = (LinearLayout) (view == null ? inflater.inflate(R.layout.view_adapter_recipe, viewGroup, false) : view);

        Recipe recipe = getItem(i);
        ((TextView) layout.findViewById(R.id.recipe_title)).setText(recipe.getName());
        // ((ImageView) layout.findViewById(R.id.recipe_image)).setImageResource(recipeList.getImage());

        return layout;
    }
}
