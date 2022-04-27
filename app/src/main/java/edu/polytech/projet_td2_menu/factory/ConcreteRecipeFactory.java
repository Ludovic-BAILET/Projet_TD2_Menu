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
    protected final int[] ratingsArray;

    public ConcreteRecipeFactory(TypesDishes typesDishes) {
        super(typesDishes);
        ratingsArray = new int[3];
    }

    @Override
    public Recipe buildRecipe(String name, List<Pair<Ingredient, Quantity>> ingredientList, String image) throws Throwable {

        buildRatings(0, 0, 0);

        switch (type) {
            case ENTREE:
                return new EntreeRecipe(name, ingredientList, ratings, image);
            case PLAT:
                return new PlatRecipe(name, ingredientList, ratings, image);
            case DESSERT:
                return new DessertRecipe(name, ingredientList, ratings, image);
            default:
                throw new Throwable("type is not valid");
        }
    }

    @Override
    public Ratings buildRatings(int sain, int price, int difficulty) {
        return ratings = new Ratings(sain,
                price,
                difficulty
        );
    }
}
