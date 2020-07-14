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
import core.Workout;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

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
            JsonObject wo = je.getAsJsonObject();
            workout = new Workout();
            workout.setDesignation(wo.get("designation").getAsString());
            int weekDay = wo.get("weekDay").getAsInt();
            workout.setWeekDay(weekDay);
            Task[] tasks = jdc.deserialize(wo.get("tasks"), Task[].class);
            for(Task task: tasks)
                workout.tasks.add(task);
               
        } catch(Exception e) {
            e.printStackTrace();
        }
        return workout;
    }
    
}
