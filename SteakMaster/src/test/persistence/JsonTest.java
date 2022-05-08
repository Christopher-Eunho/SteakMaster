package persistence;

import model.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;

// [Reference]
// I designed this class and the methods included in the class
// referring to the example of Data Persistence provided by UBC CPSC210 Course
// Original Example: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/


public class JsonTest {
    protected void checkRecipeOne(Recipe recipe) {

        assertEquals("Juicy Tenderloin", recipe.getName());
        assertEquals("Medium Rare", recipe.getDoneness());
        assertEquals("Tenderloin", recipe.getSteak().getCut());
        assertEquals(3, recipe.getSteak().getThickness());
        assertEquals(250, recipe.getSteak().getGrams());
        assertEquals(6, recipe.getStepList().size());
        assertEquals("Heat & Oil", recipe.getStepList().get(0).getName());
        assertEquals("Heat the pan and oil it", recipe.getStepList().get(0).getAction());
        assertEquals(5, recipe.getStepList().get(0).getTime());
        assertEquals("Steak on", recipe.getStepList().get(1).getName());
        assertEquals("Put the steak on and sear it", recipe.getStepList().get(1).getAction());
        assertEquals(10, recipe.getStepList().get(1).getTime());
    }

    protected void checkRecipeTwo(Recipe recipe) {

        assertEquals("Tasty Ribeye", recipe.getName());
        assertEquals("Medium Well-done", recipe.getDoneness());
        assertEquals("Ribeye", recipe.getSteak().getCut());
        assertEquals(2, recipe.getSteak().getThickness());
        assertEquals(600, recipe.getSteak().getGrams());
        assertEquals(4, recipe.getStepList().size());
        assertEquals("Heat & Oil", recipe.getStepList().get(0).getName());
        assertEquals("Heat the pan and oil it", recipe.getStepList().get(0).getAction());
        assertEquals(5, recipe.getStepList().get(0).getTime());
        assertEquals("Steak on", recipe.getStepList().get(1).getName());
        assertEquals("Put the steak on and sear it", recipe.getStepList().get(1).getAction());
        assertEquals(10, recipe.getStepList().get(1).getTime());
    }


}
