package edu.polytech.projet_td2_menu.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;

public class ViewAdapterRecipe extends BaseAdapter {

    private final LayoutInflater inflater;

    private List<Recipe> recipeList;

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
        ConstraintLayout layout = (ConstraintLayout) (view == null ? inflater.inflate(R.layout.view_adapter_recipe, viewGroup, false) : view);

        Recipe recipe = getItem(i);
        ((TextView) layout.findViewById(R.id.recipe_title)).setText(recipe.getName());

        // ((ImageView) layout.findViewById(R.id.recipe_image)).setImageDrawable(getPicture(recipe.getImageUrl()));

        return layout;
    }


    private Drawable getPicture (String urlPath ) {
        // Le drawable à renvoyer
        Drawable drawable = null ;
        try {
            Log.d("urlPath", urlPath);
            // Récupération de l'URL à partir de sa représentation sous forme de String.
            URL URL = new URL ( urlPath );
            // Ouverture de l'inputStream associé à cette URL pour sa lecture.
            InputStream is = (InputStream) URL.getContent();
            // Construction du Drawable à partir de ce flux entrant.
            drawable = Drawable.createFromStream ( is , "src" );
        } catch (IOException e ) {
            Log. e ( "Drawable_error" , e.toString());
            // Si une exception se produit faire quelque chose d'intelligent.
        }
        // Renvoyer le résultat.
        return drawable ;
    }
}
