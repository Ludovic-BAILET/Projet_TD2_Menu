package edu.polytech.projet_td2_menu.models.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.models.Ingredient;

public class ModelIngredients {
    public static final String FARINE = "farine de blé";
    private final static Map<String, Ingredient> ingredientsMap = new HashMap<>();

    static {
        ingredientsMap.put(FARINE, new Ingredient(R.drawable.farine_de_ble, FARINE));
    }

    public static Ingredient get(String ingredientName) {
        return ingredientsMap.get(ingredientName);
    }

    public static int size() {
        return ingredientsMap.size();
    }

    public static Set<Map.Entry<String, Ingredient>> entrySet() {
        return ingredientsMap.entrySet();
    }
}
