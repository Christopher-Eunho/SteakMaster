package persistence;

import model.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.util.List;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of the current recipes to file
    public void write(List<Recipe> recipes) {
        JSONObject json = new JSONObject();
        json.put("recipes", recipesToJson(recipes));

        saveToFile(json.toString(TAB));
    }

    // EFFECTS: returns recipes in this app as a JSON array
    private JSONArray recipesToJson(List<Recipe> recipes) {
        JSONArray jsonArray = new JSONArray();

        for (Recipe recipe : recipes) {
            jsonArray.put(recipe.toJson());
        }
        return jsonArray;
    }


    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
