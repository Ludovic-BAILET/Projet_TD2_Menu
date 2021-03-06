package edu.polytech.projet_td2_menu.factory;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.Quantity;
import edu.polytech.projet_td2_menu.models.Ratings;
import edu.polytech.projet_td2_menu.models.TypesDishes;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;

public abstract class AbstractRecipeFactory {

    protected String name;
    protected Ratings ratings;
    protected List<Pair<Ingredient, Quantity>> ingredients;
    protected TypesDishes type;

    public AbstractRecipeFactory(TypesDishes typesDishes) {
        ingredients = new ArrayList<>();
        this.type = typesDishes;
    }

    public abstract Recipe buildRecipe(String name, List<Pair<Ingredient, Quantity>> ingredientList, String image) throws Throwable;

    public List<Pair<Ingredient, Quantity>> getIngredientsList() {
        return ingredients;
    }

    public abstract Ratings buildRatings(int sain, int price, int difficulty);
}
