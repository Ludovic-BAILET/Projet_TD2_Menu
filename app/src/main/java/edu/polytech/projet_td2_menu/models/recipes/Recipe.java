package edu.polytech.projet_td2_menu.models.recipes;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.Quantity;
import edu.polytech.projet_td2_menu.models.Ratings;
import edu.polytech.projet_td2_menu.models.TypesDishes;

public abstract class Recipe {
    private final String name;
    private final List<Pair<Ingredient, Quantity>> ingredients;
    private Ratings ratings;
    protected TypesDishes type;

    public Recipe(String name, List<Pair<Ingredient, Quantity>> ingredients, Ratings ratings) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public List<Pair<Ingredient, Quantity>> getIngredients() {
        return ingredients;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public TypesDishes getType() {
        return type;
    }
}
