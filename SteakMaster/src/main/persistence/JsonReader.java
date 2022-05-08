package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Recipe;
import model.Steak;
import model.Step;
import org.json.*;

// [Reference]
// I designed this class and the methods included in the class
// referring to the example of Data Persistence provided by UBC CPSC210 Course
// Original Example: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/

// Represents a reader that reads recipes from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads recipe list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Recipe> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipes(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses recipes from JSON array and returns it
    private List<Recipe> parseRecipes(JSONObject recipesInJson) {
        List<Recipe> recipes = new ArrayList<Recipe>();
        JSONArray jsonArray = recipesInJson.getJSONArray("recipes"); // CONVERT JO into ARRAY
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            recipes.add(parseRecipe(nextRecipe));
        }
        return recipes;
    }

    // EFFECTS: parses recipe from JSON object and returns it
    private Recipe parseRecipe(JSONObject recipeInJson) {
        String name = recipeInJson.getString("name");
        String doneness = recipeInJson.getString("doneness");
        String picturePath = recipeInJson.getString("picturePath");
        JSONObject steakInJson = recipeInJson.getJSONObject("steak");
        JSONArray stepsInJsonArray = recipeInJson.getJSONArray("steps");

        Recipe recipe = new Recipe(name, parseSteak(steakInJson), doneness);
        List<Step> steps = parseSteps(stepsInJsonArray);

        addSteps(recipe, steps);
        recipe.setPicturePath(picturePath);
        return recipe;
    }

    // EFFECTS: parses steak from JSON object and returns it
    private Steak parseSteak(JSONObject steakInJson) {
        String cut = steakInJson.getString("cut");
        int thickness = steakInJson.getInt("thickness");
        int grams = steakInJson.getInt("grams");

        Steak steak = new Steak(cut, thickness, grams);

        return steak;

    }

    // EFFECTS: parses steps from JSON array and returns it
    private List<Step> parseSteps(JSONArray stepsInJsonArray) {
        List<Step> steps = new ArrayList<Step>();

        for (Object json: stepsInJsonArray) {
            JSONObject nextStep = (JSONObject) json;
            steps.add(parseStep(nextStep));
        }

        return steps;
    }

    // EFFECTS: parses step from JSON object and returns it
    private Step parseStep(JSONObject stepInJson) {
        String name = stepInJson.getString("name");
        String action = stepInJson.getString("action");
        int time = stepInJson.getInt("time");

        return new Step(name, action, time);
    }

    // EFFECTS: adds steps to recipe
    private void addSteps(Recipe recipe, List<Step> steps) {
        for (Step step: steps) {
            recipe.addStep(step);
        }

    }

}
