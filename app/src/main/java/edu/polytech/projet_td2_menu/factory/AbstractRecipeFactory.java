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
    }

    public abstract Recipe buildRecipe() throws Throwable;
    public abstract List<Pair<Ingredient, Quantity>> buildIngredientsList();
    public abstract Ratings buildRatings();
    public abstract void setNameRecipe(String name);
    public abstract void setType(TypesDishes type);
    public abstract void setRatingPrix(int price);
    public abstract void setRatingSain(int sain);
    public abstract void setIndexRatingDifficulty(int difficulty);
    public abstract void addIngredients(Ingredient ingredient, Quantity quantity);
}
