package edu.polytech.projet_td2_menu.network;

import static edu.polytech.projet_td2_menu.models.TypesDishes.DESSERT;
import static edu.polytech.projet_td2_menu.models.TypesDishes.ENTREE;
import static edu.polytech.projet_td2_menu.models.TypesDishes.PLAT;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import edu.polytech.projet_td2_menu.factory.ConcreteRecipeFactory;
import edu.polytech.projet_td2_menu.models.TypesDishes;
import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.Quantity;
import edu.polytech.projet_td2_menu.models.Ratings;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;

public class ApiTask extends AsyncTask<Void, Void, List<Recipe>> {

    private final static String APP_ID = "app_id=7d6e984e&";

    private final static String APP_KEY = "app_key=edb1367410cb62d49b28b3220b1e4b2b&";

    private final static String API_URL = "https://api.edamam.com/";

    private final static String RECIPE_PATH = "api/recipes/v2?type=public&";

    private final static String FIELDS = "field=label&field=image&field=ingredientLines&field=ingredients&field=totalTime&field=mealType&field=dishType";

    public final static List<Recipe> recipeList = new ArrayList<>();

    @Override
    public List<Recipe> doInBackground(Void... voids) {
        // Create URL
        try {
            Log.d("URL", "Début de création de l'URL");
            String url = API_URL + RECIPE_PATH + "q=meal&" + APP_ID + APP_KEY + FIELDS;
            Log.d("URL", "Url = " + url);
            URL edamamEndpoint = new URL(url);
            Log.d("URL", "URL crée");

            // Create connection
            Log.d("Connection_URL", "Début de connection a l'URL");
            HttpsURLConnection myConnection = (HttpsURLConnection) edamamEndpoint.openConnection();
            Log.d("Connection_URL", "Connection établie");

            Log.d("connection", ""+myConnection);

            Log.d("Response_Code", "On check le code de réponse : " + myConnection.getResponseCode());
            if (myConnection.getResponseCode() == 200) {
                // On recupère le corp de la réponse
                InputStream responseBody = myConnection.getInputStream();

                // On Crée un objet plus facil a lire
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, StandardCharsets.UTF_8);

                // On crée le Reader
                JsonReader jsonReader = new JsonReader(responseBodyReader);

                recipeList.addAll(readRecipeList(jsonReader));

                jsonReader.close();
            } else {
                Log.d("Connection API", "Erreur dans la connection a l'API");
            }
        } catch (IOException e) {
            Log.d("oui", "bonjour");
            e.printStackTrace();
        }
        return recipeList;
    }

    private List<Recipe> readRecipeList(JsonReader jsonReader) throws IOException {
        List<Recipe> recipeList = new ArrayList<>();
        jsonReader.beginObject(); // Start processing the JSON object
        while (jsonReader.hasNext()) { // Loop through all keys
            String key = jsonReader.nextName(); // Fetch the next key
            Log.d("key", ""+key);
            if (key.equals("hits")) { // Check if desired key
                Log.d("key", "on rentre dans l'objet");
                recipeList.addAll(readRecipes(jsonReader));
                break; // Break out of the loop
            } else {
                jsonReader.skipValue(); // Skip values of other keys
            }
        }
        Log.d("List size", "la taille de la liste est de : " + recipeList.size());
        Log.d("Liste_recipe", ""+recipeList);
        return recipeList;
    }

    private List<Recipe> readRecipes(JsonReader jsonReader) throws IOException {
        List<Recipe> recipeList = new ArrayList<>();

        jsonReader.beginArray();
        while (jsonReader.hasNext()){
            recipeList.addAll(readOneRecipe(jsonReader));
        }
        jsonReader.endArray();
        return recipeList;
    }

    private List<Recipe> readOneRecipe(JsonReader jsonReader) throws IOException {
        List<Recipe> recipes = new ArrayList<>();

        jsonReader.beginObject();
        while (jsonReader.hasNext()){
            if (jsonReader.nextName().equals("recipe"))
                recipes.addAll(createRecipes(jsonReader));
            else
                jsonReader.skipValue();
        }
        jsonReader.endObject();
        return recipes;
    }

    private List<Recipe> createRecipes(JsonReader jsonReader) throws IOException {
        String label = null;
        List<Pair<Ingredient, Quantity>> ingredientList = new ArrayList<>();
        int time = 0;
        List<TypesDishes> typesDishesList = new ArrayList<>();

        List<Recipe> recipeList = new ArrayList<>();
        jsonReader.beginObject();
        while (jsonReader.hasNext()){
            String key = jsonReader.nextName();
            switch (key){
                case "label":
                    label = jsonReader.nextString();
                    break;

                case "ingredients":
                    ingredientList = readIngredientList(jsonReader);
                    break;

                case "totalTime":
                    time = jsonReader.nextInt();
                    break;

                case "dishType":
                    typesDishesList.addAll(readDishesTypesList(jsonReader));
                    break;

                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();

        ConcreteRecipeFactory concreteRecipeFactoryEnteree = new ConcreteRecipeFactory(ENTREE);
        ConcreteRecipeFactory concreteRecipeFactoryPlat = new ConcreteRecipeFactory(PLAT);
        ConcreteRecipeFactory concreteRecipeFactoryDessert = new ConcreteRecipeFactory(DESSERT);
        for (TypesDishes dishiesTypes : typesDishesList) {
            try {
                Recipe recipe = null;
                switch (dishiesTypes){
                    case ENTREE:
                        concreteRecipeFactoryEnteree.setNameRecipe(label);
                        for (Pair<Ingredient, Quantity> pair : ingredientList){
                            concreteRecipeFactoryEnteree.addIngredients(pair.first, pair.second);
                        }
                        concreteRecipeFactoryEnteree.buildIngredientsList();
                        concreteRecipeFactoryEnteree.buildRatings();
                        recipe = concreteRecipeFactoryDessert.buildRecipe();
                        break;
                    case PLAT:
                        concreteRecipeFactoryPlat.setNameRecipe(label);
                        for (Pair<Ingredient, Quantity> pair : ingredientList){
                            concreteRecipeFactoryPlat.addIngredients(pair.first, pair.second);
                        }
                        concreteRecipeFactoryPlat.buildIngredientsList();
                        concreteRecipeFactoryPlat.buildRatings();
                        recipe = concreteRecipeFactoryPlat.buildRecipe();
                        break;
                    case DESSERT:
                        concreteRecipeFactoryDessert.setNameRecipe(label);
                        for (Pair<Ingredient, Quantity> pair : ingredientList){
                            concreteRecipeFactoryEnteree.addIngredients(pair.first, pair.second);
                        }
                        concreteRecipeFactoryDessert.buildIngredientsList();
                        concreteRecipeFactoryDessert.buildRatings();
                        recipe = concreteRecipeFactoryDessert.buildRecipe();
                        break;
                }
                recipeList.add(recipe);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        return recipeList;
    }



    private List<TypesDishes> readDishesTypesList(JsonReader jsonReader) throws IOException {
        List<TypesDishes> typesDishesList = new ArrayList<>();

            jsonReader.beginArray(); // Start processing the JSON object

            while (jsonReader.hasNext()) { // Loop through all keys
                String type = jsonReader.nextString();
                Log.d("sidhType", ""+type+", hasNext :"+jsonReader.hasNext()+", "+jsonReader);

                switch (type){
                    case "desserts":
                        typesDishesList.add(DESSERT);
                        break;
                    case "starter":
                        typesDishesList.add(ENTREE);
                        break;
                    case "main course":
                        typesDishesList.add(PLAT);
                        break;
                }
            }
            jsonReader.endArray();
        return typesDishesList;
    }

    private List<Pair<Ingredient, Quantity>> readIngredientList(JsonReader jsonReader) throws IOException {
        List<Pair<Ingredient, Quantity>> list = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()){
            list.add(readIngredien(jsonReader));
        }
        jsonReader.endArray();
        return list;
    }

    private Pair<Ingredient, Quantity> readIngredien(JsonReader jsonReader) throws IOException {
        Pair<Ingredient, Quantity> paire =  null;
        double quantity = -1;
        String measure = null;
        String name = null;
        String image = null;

        jsonReader.beginObject(); // Start processing the JSON object

        while (jsonReader.hasNext()) { // Loop through all keys
            String key = jsonReader.nextName(); // Fetch the next key
            switch (key){
                case "quantity":
                    quantity = jsonReader.nextDouble();
                    break;
                case "measure":
                    try {
                        measure = jsonReader.nextString();
                    } catch (Exception ignored) {
                        jsonReader.nextNull();
                        measure = "no measure";
                    }

                    break;
                case "food":
                    name = jsonReader.nextString();
                    break;
                case "image":
                    try{
                        image = jsonReader.nextString();
                    } catch (Exception ignored) {
                        jsonReader.nextNull();
                        image = "no image";
                    }
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        paire = new Pair<>(new Ingredient(image, name), new Quantity(quantity, measure));
        return paire;
    }
}
