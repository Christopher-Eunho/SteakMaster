package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a steak having cut(which part of beef it is), thickness(in mm), and grams(weight)
public class Steak implements Writable {


    private String cut;     // cut of the steak (which part of the beef)
    private int thickness;  // thickness of the steak in mm
    private int grams;      // weight of the steak in g(grams)


    // REQUIRES: cut has non-zero length;
    //           thickness > 0;
    //           grams > 0;
    // MODIFIES: this
    // EFFECTS: cut of the steak is set to the parameter cut;
    //          thickness of the steak is set to the parameter thickness;
    //          grams of the steak is set to that parameter grams
    public Steak(String cut, int thickness, int grams) {
        this.cut = cut;
        this.thickness = thickness;
        this.grams = grams;
    }


    public int getThickness() {
        return thickness;
    }

    public int getGrams() {
        return grams;
    }

    public String getCut() {
        return cut;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cut", cut);
        json.put("thickness", thickness);
        json.put("grams", grams);

        return json;
    }
}

