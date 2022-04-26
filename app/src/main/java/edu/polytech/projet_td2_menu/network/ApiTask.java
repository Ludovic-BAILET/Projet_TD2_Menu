package edu.polytech.projet_td2_menu.network;

import static edu.polytech.projet_td2_menu.models.DishesTypes.DESSERT;
import static edu.polytech.projet_td2_menu.models.DishesTypes.ENTREE;
import static edu.polytech.projet_td2_menu.models.DishesTypes.PLAT;

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
import java.util.Collection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import edu.polytech.projet_td2_menu.factory.RecipeFactory;
import edu.polytech.projet_td2_menu.models.DishesTypes;
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

    public static List<Recipe> recipeList = null;

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
        return recipeList;
    }

    private List<Recipe> readRecipes(JsonReader jsonReader) throws IOException {
        List<Recipe> recipeList = new ArrayList<>();

        jsonReader.beginArray();
        while (jsonReader.hasNext()){
            recipeList.addAll(readOneRecipe(jsonReader));
        }
        jsonReader.beginArray();
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
        List<Pair<Ingredient, Quantity>> ingredientList = null;
        int time = 0;
        List<DishesTypes> dishesTypesList = null;

        List<Recipe> recipeList = new ArrayList<>();
        Log.d("jsonReader", ""+jsonReader);
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
                    dishesTypesList = readDishesTypesList(jsonReader);
                    break;

                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();

        RecipeFactory recipeFactory = new RecipeFactory();
        for (DishesTypes dishiesTypes : dishesTypesList) {
            try {
                recipeList.add(recipeFactory.buildRecipe(dishiesTypes, label, ingredientList, new Ratings(0, 0, 0)));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        return recipeList;
    }



    private List<DishesTypes> readDishesTypesList(JsonReader jsonReader) throws IOException {
        List<DishesTypes> dishesTypesList = new ArrayList<>();

            jsonReader.beginArray(); // Start processing the JSON object

            while (jsonReader.hasNext()) { // Loop through all keys
                String type = jsonReader.nextString();

                switch (type){
                    case "desserts":
                        dishesTypesList.add(DESSERT);
                        break;
                    case "starter":
                        dishesTypesList.add(ENTREE);
                        break;
                    case "main course":
                        dishesTypesList.add(PLAT);
                        break;
                    default:
                        jsonReader.skipValue();
                }
            }
            jsonReader.endArray();
        return dishesTypesList;
    }

    private List<Pair<Ingredient, Quantity>> readIngredientList(JsonReader jsonReader) throws IOException {
        List<Pair<Ingredient, Quantity>> list = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()){
            list.add(readIngredien(jsonReader));
        }
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
            Log.d("ingredient_key", ""+key+", hasNext : "+jsonReader.hasNext());
            Log.d("ingredient_key", ""+jsonReader);
            switch (key){
                case "quantity":
                    quantity = jsonReader.nextDouble();
                    break;
                case "measure":
                    try {
                        measure = jsonReader.nextString();
                    } catch (IOException ignored) {

                    }
                    Log.d("ta_mere_la_pute", ""+jsonReader);
                    break;
                case "food":
                    name = jsonReader.nextString();
                    break;
                case "image":
                    image = jsonReader.nextString();
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
