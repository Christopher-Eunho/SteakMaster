package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a step of a steak cooking recipe having a name, action and time required for cooking
public class Step extends Clock implements Writable {

    private String name;        // name of the step
    private String action;      // required action of the step


    // REQUIRES: both stepName and action have a non-zero length;
    //          time > 0
    // MODIFIES: this
    // EFFECTS: sets name on step to stepName;
    //          sets action of step to the parameter action;
    //          sets time of step to the parameter time;
    public Step(String stepName, String action, int time) {
        name = stepName;
        this.action = action;
        addTime(time);
    }



    public String getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("action", action);
        json.put("time", getTime());
        return json;

    }
}




