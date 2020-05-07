package com.mu.jan.sandwich_club.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.mu.jan.sandwich_club.Model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class SandwichJson {

    public static Sandwich parseJson(String json) {


        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        Sandwich sandwichObject = null;

        try {

            // Create a JSONObject from the JSON string
            JSONObject baseJsonObject = new JSONObject(json);

            // Extract the JSONObject associated with the key called "name"
            JSONObject name = baseJsonObject.getJSONObject("name");

            // Extract the mainName from the key called "mainName"
            String mainName = name.getString("mainName");

            List<String> alsoKnownAsList = new ArrayList<>();
            // Extract the JSONArray associated with the key called "alsoKnownAs"
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            int countAlsoKnownAsArray = alsoKnownAsArray.length();
            // For each name in the JSONArray, add it into the ArrayList
            for (int i = 0; i < countAlsoKnownAsArray; i++) {
                String otherName = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(otherName);
            }

            String placeOfOrigin = baseJsonObject.getString("placeOfOrigin");
            String description = baseJsonObject.getString("description");
            String image = baseJsonObject.getString("image");

            List<String> ingredientsList = new ArrayList<>();

            JSONArray ingredientsArray = baseJsonObject.getJSONArray("ingredients");
            int countIngredientsArray = ingredientsArray.length();

            // For each ingredient in the JSONArray, add it into the ArrayList
            for (int j = 0; j < countIngredientsArray; j++) {
                String ingredient = ingredientsArray.getString(j);
                ingredientsList.add(ingredient);
            }

            // Create the Sandwich Object with the data from the Json
            sandwichObject = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);


        } catch (JSONException e) {
            //error while parsing json
            Log.e("JsonUtils", "Error while parsing the Sandwich JSON Object", e);
        }

        // return the Sandwich Object
        return sandwichObject;
    }
}