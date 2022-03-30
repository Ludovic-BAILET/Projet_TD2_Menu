package edu.polytech.projet_td2_menu.models;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {
    private static final String RATING_SAIN = "ratingSain";
    private static final String RATING_PRIX = "ratingPrix";
    private static final String RATING_DIFFICULTE = "ratingDifficulte";

    private final String name;
    private final List<Pair<Ingredient, Quantity>> ingredients;
    private final Map<String, Integer> ratings;
    private final TypesDishes type;

    public Recipe(String name, TypesDishes type, List<Pair<Ingredient, Quantity>> ingredients, int ratingSain, int ratingPrix, int ratingDifficulte) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.type = type;
        ratings = new HashMap<>();
        ratings.put(RATING_SAIN, ratingSain);
        ratings.put(RATING_PRIX, ratingPrix);
        ratings.put(RATING_DIFFICULTE, ratingDifficulte);
    }

    public String getName() {
        return name;
    }

    public List<Pair<Ingredient, Quantity>> getIngredients() {
        return ingredients;
    }

    public Integer getRatingSain() {
        return ratings.get(RATING_PRIX);
    }

    public Integer getRatingPrix() {
        return ratings.get(RATING_PRIX);
    }

    public Integer getRatingDifficulte() {
        return ratings.get(RATING_DIFFICULTE);
    }

    public TypesDishes getType() {
        return type;
    }
}
