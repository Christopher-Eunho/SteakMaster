package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a recipe for cooking a steak having detailed steps with precise time required for each step
// and specifications of the steak
public class Recipe extends Clock implements Writable {

    private String name;            // Name of the recipe
    private String doneness;        // Doneness of the steak recipe
    private Steak steak;            // Steak of the recipe with specifications
    private List<Step> stepList;    // List of steps to be done for cooking this recipe



    private String picturePath; // Relative path of the recipe picture



    //REQUIRES: both recipeName and doneness have a non-zero length
    //MODIFIES: this
    // EFFECTS: sets name on recipe to recipeName;
    //         sets steak of recipe to the parameter steak;
    //          sets doneness of recipe to the parameter doneness;
    public Recipe(String recipeName, Steak steak, String doneness) {
        name = recipeName;
        this.doneness = doneness;
        this.steak = steak;
        stepList = new ArrayList<Step>();
        picturePath = "";
    }


    // MODIFIES: this
    // EFFECTS: adds a new step to stepList;
    //          adds the time of the step to the total time of the recipe
    public void addStep(Step step) {
        addTime(step.getTime());
        stepList.add(step);
        EventLog.getInstance().logEvent(new Event("Step [" + step.getName() + "] is added to " + name));
    }


    // EFFECTS: returns current step in the recipe according to time spent on the recipe
    public Step currentStep() {
        int timeToBeSubtracted = timeSpent;
        for (Step next: stepList) {
            if (next.getTime() > timeToBeSubtracted) {
                return next;
            }
            timeToBeSubtracted -= next.getTime();
        }
        return stepList.get(stepList.size() - 1);
    }

    // EFFECTS: returns the name of the step in the recipe according to time spent on the recipe;
    //          returns final step message if the current step is the last step in the recipe
    public String nextStepName() {
        int currentIndex = stepList.indexOf(currentStep());
        if (currentIndex == stepList.size() - 1) {
            return "Nope. You are in the final step!";
        }
        return stepList.get(currentIndex + 1).getName();
    }

    // MODIFIES: this
    // EFFECTS: resets all spent times in the recipe and it steps to 0
    public void resetTimeAll() {
        resetTimeLeft();
        for (Step next: stepList) {
            next.resetTimeLeft();
        }
    }


    public List<Step> getStepList() {
        return stepList;
    }

    public String getName() {
        return name;
    }

    public String getDoneness() {
        return doneness;
    }

    public Steak getSteak() {
        return steak;
    }

    public String getPicturePath() {
        return picturePath;
    }

    // REQUIRES: picturePath should be a relative address of files inside "./data/pic"
    // MODIFIES: this
    // EFFECTS: set this.picturePath to given input and save log the event in EventLog object
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        EventLog.getInstance().logEvent(new Event("Image at " + picturePath + " is added to " + name));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("doneness", doneness);
        json.put("steak", steak.toJson());
        json.put("steps", stepsToJson());
        json.put("picturePath", picturePath);
        return json;
    }

    // EFFECTS: returns steps in this recipe as a JSON array
    private JSONArray stepsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Step step : stepList) {
            jsonArray.put(step.toJson());
        }
        return jsonArray;

    }


}
