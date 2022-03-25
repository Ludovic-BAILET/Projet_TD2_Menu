package edu.polytech.projet_td2_menu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.data.ModelIngredients;

public class ViewAdapterIngredient extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<Map.Entry<String, Ingredient>> ingredientsList;

    public ViewAdapterIngredient(Context context) {
        inflater = LayoutInflater.from(context);
        ingredientsList = new ArrayList<>(ModelIngredients.entrySet());
    }

    @Override
    public int getCount() {
        return ModelIngredients.size();
    }

    @Override
    public Map.Entry<String, Ingredient> getItem(int i) {
        return ingredientsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout = (LinearLayout) (view == null ? inflater.inflate(R.layout.view_adapter_ingredient, viewGroup, false) : view);

        Ingredient ingredient = getItem(i).getValue();
        ((ImageView) layout.findViewById(R.id.image)).setImageResource(ingredient.getImage());
        ((TextView) layout.findViewById(R.id.name)).setText(ingredient.getName());

        return layout;
    }
}
