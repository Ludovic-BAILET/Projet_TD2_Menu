package edu.polytech.projet_td2_menu.models.recipes;

import android.util.Pair;

import java.util.List;

import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.Quantity;
import edu.polytech.projet_td2_menu.models.Ratings;
import edu.polytech.projet_td2_menu.models.DishesTypes;

public class PlatRecipe extends Recipe {

    public PlatRecipe(String name, List<Pair<Ingredient, Quantity>> ingredients, Ratings ratings) {
        super(name, ingredients, ratings);
        this.type = DishesTypes.PLAT;

    }
}
