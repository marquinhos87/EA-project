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
import core.Task;
import core.TaskComparatorById;
import core.Workout;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author joaomarques
 */
public class WorkoutDeserializer implements JsonDeserializer<Workout> {

    /**
     * Deserialize a JsonElement to a Workout.
     * 
     * @param je The JsonElement to deserialize.
     * @param type
     * @param jdc
     * @return A Workout.
     * @throws JsonParseException 
     */
    @Override
    public Workout deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Workout workout = null;
        try {
            JsonObject wo = je.getAsJsonObject(); // Workout Object
            workout = new Workout();
            workout.id = wo.get("id").getAsInt();
            workout.date = jdc.deserialize(wo.get("date"), Date.class);
            workout.designation = wo.get("designation").getAsString();
            workout.done = wo.get("done").getAsBoolean();
            Task[] tasks = jdc.deserialize(wo.get("tasks"), Task[].class);
            workout.tasks.addAll(Arrays.asList(tasks));
            workout.tasks.sort(new TaskComparatorById());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return workout;
    }
    
}
