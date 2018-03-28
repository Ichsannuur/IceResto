package com.ics.mcb.Object;

import java.util.List;

/**
 * Created by Ichsan.Fatiha on 1/4/2018.
 */

public class Value {
    String value;
    String message;
    List<Food> food;

    public List<Food> getFoodList() {
        return food;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
