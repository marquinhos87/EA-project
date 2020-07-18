/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseJSON.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import core.Workout;
import java.lang.reflect.Type;

/**
 *
 * @author joaomarques
 */
public class WorkoutSerializer implements JsonSerializer<Workout> {

    /**
     * Serialize a Workout to a JsonElement.
     * 
     * @param workout The workout to serialize.
     * @param type
     * @param jsc
     * @return A JsonElement with a Workout inside.
     */
    @Override
    public JsonElement serialize(Workout workout, Type type, JsonSerializationContext jsc) {
        final JsonObject jsonObj = new JsonObject();
        jsonObj.add("id",jsc.serialize(workout.getID()));
        jsonObj.add("designation",jsc.serialize(workout.getDesignation()));
        jsonObj.add("done",jsc.serialize(workout.getDone()));
        jsonObj.add("date",jsc.serialize(workout.getDate()));
        jsonObj.add("tasks",jsc.serialize(workout.tasks.toArray()));
        return jsonObj;
    }
    
}
