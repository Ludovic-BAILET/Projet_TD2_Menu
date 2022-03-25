package edu.polytech.projet_td2_menu.models.data;

import static edu.polytech.projet_td2_menu.models.TypesUnits.*;
import static edu.polytech.projet_td2_menu.models.data.ModelIngredients.*;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.Quantity;
import edu.polytech.projet_td2_menu.models.Recipe;
import edu.polytech.projet_td2_menu.models.TypesDishes;
import edu.polytech.projet_td2_menu.models.TypesUnits;

public class ModelRecipes {
    private final static List<Recipe> recipesList = new ArrayList<>();

    static {
        List<Pair<Ingredient, Quantity>> ingredientsPizza = new ArrayList<>();
        ingredientsPizza.add(new Pair<>(ModelIngredients.get(FARINE), new Quantity(200, G)));
        recipesList.add(new Recipe("Pizza", TypesDishes.PLAT, ingredientsPizza, 1, 2, 2));
    }

    public static Recipe get(int index) {
        return recipesList.get(index);
    }

    public static int size() {
        return recipesList.size();
    }
}
