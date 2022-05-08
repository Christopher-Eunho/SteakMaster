package model;

import java.util.ArrayList;

import java.util.List;

// Represents a recipe manager that handles tasks related to recipes of SteakMasterUI
public class RecipeManager {


    private List<Recipe> recipes;


    public RecipeManager() {
        recipes = new ArrayList<Recipe>();
        addDefaultRecipes();
    }

    public void add(Recipe recipe) {
        recipes.add(recipe);
        EventLog.getInstance().logEvent(new Event("Added recipe: " + recipe.getName()));
    }

    public void remove(int index) {
        String recipeName = recipes.get(index).getName();

        EventLog.getInstance().logEvent(new Event("Removed recipe: " + recipeName));
        recipes.remove(index);
    }


    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }


    public ArrayList<Recipe> getRecipes() {
        return (ArrayList<Recipe>) recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }


    // MODIFIES: this
    // EFFECTS: Initialize recipe list with two basic recipes
    private void addDefaultRecipes() {
        Steak tenderloin = new Steak("Tenderloin", 3, 250);
        Steak ribeye = new Steak("Ribeye", 2, 600);

        Recipe sampleRecipe = new Recipe("Juicy Tenderloin", tenderloin, "Medium Rare");
        Recipe sampleRecipe2 = new Recipe("Tasty Ribeye", ribeye, "Medium Well-done");

        sampleRecipe.addStep(new Step("Heat & Oil", "Heat the pan and oil it", 5));
        sampleRecipe.addStep(new Step("Steak on", "Put the steak on and sear it", 10));
        sampleRecipe.addStep(new Step("Sear other side", "Flip the steak and sear other side", 10));
        sampleRecipe.addStep(new Step("Butter it", "Lower the heat and Give a butter shower to add flavor", 6));
        sampleRecipe.addStep(new Step("Add rosemary", "Add the herb and keep the butter shower", 4));
        sampleRecipe.addStep(new Step("Resting", "Put the steak on a cooling rack and leave it", 14));

        sampleRecipe2.addStep(new Step("Heat & Oil", "Heat the pan and oil it", 5));
        sampleRecipe2.addStep(new Step("Steak on", "Put the steak on and sear it", 10));
        sampleRecipe2.addStep(new Step("Sear other side", "Flip the steak and sear other side", 10));
        sampleRecipe2.addStep(new Step("Butter it", "Lower the heat and Give a butter shower to add flavor", 6));

        sampleRecipe.setPicturePath("./data/pic/1.jpg");
        sampleRecipe2.setPicturePath("./data/pic/2.jpg");

        add(sampleRecipe);
        add(sampleRecipe2);

    }


}
