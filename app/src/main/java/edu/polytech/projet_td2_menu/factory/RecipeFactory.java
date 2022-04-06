package edu.polytech.projet_td2_menu.factory;

import android.util.Pair;

import java.util.List;

import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.Quantity;
import edu.polytech.projet_td2_menu.models.Ratings;
import edu.polytech.projet_td2_menu.models.TypesDishes;
import edu.polytech.projet_td2_menu.models.recipes.DessertRecipe;
import edu.polytech.projet_td2_menu.models.recipes.EntreeRecipe;
import edu.polytech.projet_td2_menu.models.recipes.PlatRecipe;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;

public class RecipeFactory {

    public Recipe buildRecipe(TypesDishes type, String name, List<Pair<Ingredient, Quantity>> ingredients, Ratings ratings) throws Throwable {
        switch (type) {
            case ENTREE:
                return new EntreeRecipe(name, ingredients, ratings);
            case PLAT:
                return new PlatRecipe(name, ingredients, ratings);
            case DESSERT:
                return new DessertRecipe(name, ingredients, ratings);
            default:
                throw new Throwable("type is not valid");
        }
    }
}
