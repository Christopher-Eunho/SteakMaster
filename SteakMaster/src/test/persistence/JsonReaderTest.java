package persistence;

import model.Recipe;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// [Reference]
// I designed this class and the methods included in the class
// referring to the example of Data Persistence provided by UBC CPSC210 Course
// Original Example: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/

class JsonReaderTest extends JsonTest  {



    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Recipe> recipes = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipes() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipes.json");
        try {
            List<Recipe> recipes = reader.read();
            assertEquals(0, recipes.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRecipesFirst() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecipes.json");
        try {

            List<Recipe> recipes = reader.read();
            assertEquals(2, recipes.size());
            checkRecipeOne(recipes.get(0));
            checkRecipeTwo(recipes.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}