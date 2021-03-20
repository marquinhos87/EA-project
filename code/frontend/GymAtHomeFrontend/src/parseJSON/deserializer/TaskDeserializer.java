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
import core.Serie;
import core.SerieComparatorById;
import core.Task;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 *
 * @author joaomarques
 */
public class TaskDeserializer implements JsonDeserializer<Task> {

    /**
     * Deserialize a JsonElement to a Task.
     * 
     * @param je The JsonElement to deserialize.
     * @param type
     * @param jdc
     * @return A Task.
     * @throws JsonParseException 
     */
    @Override
    public Task deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Task task = null;
        try {
            JsonObject to = je.getAsJsonObject(); // Task Object
            task = new Task();
            task.designation = to.get("designation").getAsString();
            task.duration = to.get("duration").getAsString();
            task.rest = to.get("rest").getAsString();
            task.equipment = to.get("equipment").getAsString();
            Serie[] series = jdc.deserialize(to.get("series"), Serie[].class);
            task.series.addAll(Arrays.asList(series));
            task.series.sort(new SerieComparatorById());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return task;
    }
    
}
