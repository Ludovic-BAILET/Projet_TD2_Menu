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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import edu.polytech.projet_td2_menu.factory.RecipeFactory;
import edu.polytech.projet_td2_menu.models.DishesTypes;
import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.Quantity;
import edu.polytech.projet_td2_menu.models.Ratings;
import edu.polytech.projet_td2_menu.models.recipes.Recipe;

public class ApiTask extends AsyncTask<Void, Void, Void> {

    private final static String APP_ID = "app_id=7d6e984e&";

    private final static String APP_KEY = "app_key=edb1367410cb62d49b28b3220b1e4b2b&";

    private final static String API_URL = "https://api.edamam.com/";

    private final static String RECIPE_PATH = "api/recipes/v2?type=public&";

    private final static String FIELDS = "field=label&field=image&field=ingredientLines&field=ingredients&field=totalTime&field=mealType&field=dishType";


    public ApiTask (){
    }

    @Override
    public Void doInBackground(Void... voids) {
        // Create URL
        URL edamamEndpoint = null;
        try {
            Log.d("URL", "Début de création de l'URL");
            edamamEndpoint = new URL(API_URL + RECIPE_PATH + "q=meal&" + APP_ID + APP_KEY + FIELDS);
            Log.d("URL", "URL crée");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Create connection
        HttpsURLConnection myConnection = null;
        try {
            Log.d("Connection_URL", "Début de connection a l'URL");
            myConnection = (HttpsURLConnection) edamamEndpoint.openConnection();
            Log.d("Connection_URL", "Connection établie");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

        try {
            Log.d("Response_Code", "On check le code de réponse : ");
            Log.d("Response_Code", "" + myConnection.getResponseCode());
            if (myConnection.getResponseCode() == 200) {
                // On recupère le corp de la réponse
                InputStream responseBody = myConnection.getInputStream();

                // On Crée un objet plus facil a lire
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, StandardCharsets.UTF_8);

                // On crée le Reader
                JsonReader jsonReader = new JsonReader(responseBodyReader);

                readRecipeList(jsonReader);

                jsonReader.close();
            } else {
                Log.d("Connection API", "Erreur dans la connection a l'API");
            }
        } catch (IOException e) {
            Log.d("Fail", "Fail get response code");
            e.printStackTrace();
        }
        return null;
    }

    private List<Recipe> readRecipeList(JsonReader jsonReader) {
        List<Recipe> recipeList = new ArrayList<>();
        try {
            jsonReader.beginArray(); // Start processing the JSON object
            while (jsonReader.hasNext()) { // Loop through all keys
                String key = jsonReader.nextName(); // Fetch the next key
                if (key.equals("hits")) { // Check if desired key
                    recipeList.addAll(readRecipe(jsonReader));
                    break; // Break out of the loop
                } else {
                    jsonReader.skipValue(); // Skip values of other keys
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("List size", "la taille de la liste est de : " + recipeList.size());
        return recipeList;
    }

    private List<Recipe> readRecipe(JsonReader jsonReader) throws IOException {
        String label = null;
        List<Pair<Ingredient, Quantity>> ingredientList = null;
        int time = 0;
        List<DishesTypes> dishesTypesList = null;

        List<Recipe> recipeList = new ArrayList<>();

        try {
            jsonReader.beginObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                if (!jsonReader.hasNext()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String key = null;
            try {
                key = jsonReader.nextName();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private List<DishesTypes> readDishesTypesList(JsonReader jsonReader) {
        List<DishesTypes> dishesTypesList = new ArrayList<>();
        try {

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dishesTypesList;
    }

    private List<Pair<Ingredient, Quantity>> readIngredientList(JsonReader jsonReader) {
        List<Pair<Ingredient, Quantity>> ingredientList =  new ArrayList<>();
        try {
            int quantity = -1;
            String measure = null;
            String name = null;
            String image = null;

            jsonReader.beginObject(); // Start processing the JSON object

            while (jsonReader.hasNext()) { // Loop through all keys
                String key = jsonReader.nextName(); // Fetch the next key

                switch (key){
                    case "quantity":
                        quantity = jsonReader.nextInt();
                        break;
                    case "measure":
                        measure = jsonReader.nextString();
                        break;
                    case "food":
                        name = jsonReader.nextString();
                        break;
                    case "image":
                        image = jsonReader.nextString();
                    default:
                        jsonReader.skipValue();
                }

                ingredientList.add(new Pair<>(new Ingredient(image, name), new Quantity(quantity, measure)));
            }

            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredientList;
    }
}
