package persistence;

import model.Recipe;
import model.Steak;
import model.Step;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// [Reference]
// I designed this class and the methods included in the class
// referring to the example of Data Persistence provided by UBC CPSC210 Course
// Original Example: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRecipes() {
        try {
            List<Recipe> recipes = new ArrayList<Recipe>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipes.json");
            writer.open();
            writer.write(recipes);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecipes.json");
            recipes = reader.read();
            assertEquals(0, recipes.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRecipes() {
        try {
            List<Recipe> recipes = getGeneralRecipes();

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRecipes.json");
            writer.open();
            writer.write(recipes);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRecipes.json");
            recipes = reader.read();
            assertEquals(2, recipes.size());
            checkRecipeOne(recipes.get(0));
            checkRecipeTwo(recipes.get(1));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }


    private List<Recipe> getGeneralRecipes() {
        List<Recipe> recipes = new ArrayList<Recipe>();

        Steak steak = new Steak("Tenderloin", 3, 250);
        Recipe recipe = new Recipe("Juicy Tenderloin",
                steak, "Medium Rare");

        recipe.setPicturePath("./data/pic/1.jpg");
        recipe.addStep(new Step("Heat & Oil",
                "Heat the pan and oil it",
                5));
        recipe.addStep(new Step("Steak on",
                "Put the steak on and sear it",
                10));
        recipe.addStep(new Step("Sear other side",
                "Flip the steak and sear other side",
                10));
        recipe.addStep(new Step("Butter it",
                "Lower the heat and Give a butter shower to add flavor",
                6));
        recipe.addStep(new Step("Add rosemary",
                "Add the herb and keep the butter shower",
                4));
        recipe.addStep(new Step("Resting",
                "Put the steak on a cooling rack and leave it",
                14));

        recipes.add(recipe);

        steak = new Steak("Ribeye", 2, 600);
        recipe = new Recipe("Tasty Ribeye", steak, "Medium Well-done");

        recipe.addStep(new Step("Heat & Oil",
                "Heat the pan and oil it",
                5));
        recipe.addStep(new Step("Steak on",
                "Put the steak on and sear it",
                10));

        recipe.addStep(new Step("Sear other side",
                "Flip the steak and sear other side",
                10));
        recipe.addStep(new Step("Butter it",
                "Lower the heat and Give a butter shower to add flavor",
                6));

        recipe.setPicturePath("./data/pic/2.jpg");
        recipes.add(recipe);
        return recipes;
    }
}
