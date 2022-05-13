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
    private final String imageUrl;
    protected TypesDishes type;
    private Ratings ratings;

    public Recipe(String name, List<Pair<Ingredient, Quantity>> ingredients, Ratings ratings, String imageUrl) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.ratings = ratings;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
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
