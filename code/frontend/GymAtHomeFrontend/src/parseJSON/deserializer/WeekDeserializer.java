/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseJSON.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import core.Week;
import core.Workout;
import core.WorkoutComparatorByDate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author joaomarques
 */
public class WeekDeserializer implements JsonDeserializer<Week> {

    /**
     * Deserialize a JsonElement to a Week.
     * 
     * @param je The JsonElement to deserialize.
     * @param type
     * @param jdc
     * @return A Week.
     * @throws JsonParseException 
     */
    @Override
    public Week deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Week week = null;
        try {
            JsonObject wo = je.getAsJsonObject(); // Week Object
            week = new Week();
            week.number = wo.get("number").getAsInt();
            week.initialDate = jdc.deserialize(wo.get("initialDate"), Date.class);
            week.finalDate = jdc.deserialize(wo.get("finalDate"), Date.class);
            Workout[] workouts = jdc.deserialize(wo.get("workouts"), Workout[].class);
            for(Workout workout: workouts) {
                week.workouts.put(workout.id, workout);
            }
            // sorted List
            week.workoutsList = new ArrayList<>(week.workouts.values());
            week.workoutsList.sort(new WorkoutComparatorByDate());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return week;
    }
    
}
