package edu.polytech.projet_td2_menu.models.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import edu.polytech.projet_td2_menu.models.recipes.Recipe;

public class ModelRecipes extends Observable {

    private final List<Recipe> recipeList;

    public ModelRecipes() {
        recipeList = new ArrayList<>();
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList.addAll(recipeList);
        setChanged();
        notifyObservers(this.recipeList);
    }
}
