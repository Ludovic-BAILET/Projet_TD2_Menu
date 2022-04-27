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

public class ConcreteRecipeFactory extends AbstractRecipeFactory {
    private static final int INDEX_RATING_PRICE = 1;
    private static final int INDEX_RATING_SAIN = 0;
    private static final int INDEX_RATING_DIFFICULTY = 2;
    private final int[] ratingsArray;

    public ConcreteRecipeFactory(TypesDishes typesDishes) {
        super(typesDishes);
        ratingsArray = new int[3];
    }

    @Override
    public Recipe buildRecipe(String name, List<Pair<Ingredient, Quantity>> ingredientList, String image) throws Throwable {

        setNameRecipe(name);

        addIngredientsList(ingredientList);

        buildRatings();

        switch (type) {
            case ENTREE:
                return new EntreeRecipe(name, ingredients, ratings, image);
            case PLAT:
                return new PlatRecipe(name, ingredients, ratings, image);
            case DESSERT:
                return new DessertRecipe(name, ingredients, ratings, image);
            default:
                throw new Throwable("type is not valid");
        }
    }

    @Override
    public List<Pair<Ingredient, Quantity>> getIngredientsList() {
        return ingredients;
    }

    @Override
    public Ratings buildRatings() {
        return ratings = new Ratings(ratingsArray[INDEX_RATING_SAIN],
                ratingsArray[INDEX_RATING_PRICE],
                ratingsArray[INDEX_RATING_DIFFICULTY]);
    }

    @Override
    public void setNameRecipe(String name) {
        this.name = name;
    }

    @Override
    public void setType(TypesDishes type) {
        this.type = type;
    }

    public void setRatingPrix(int price) {
        ratingsArray[INDEX_RATING_PRICE] = price;
    }

    public void setRatingSain(int sain) {
        ratingsArray[INDEX_RATING_SAIN] = sain;
    }

    public void setIndexRatingDifficulty(int difficulty) {
        ratingsArray[INDEX_RATING_DIFFICULTY] = difficulty;
    }

    public void addIngredients(Ingredient ingredient, Quantity quantity) {
        ingredients.add(new Pair<>(ingredient, quantity));
    }

    public void addIngredientsList(List<Pair<Ingredient, Quantity>> list) {
        ingredients.addAll(list);
    }
}
