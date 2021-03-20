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
import core.Task;
import java.lang.reflect.Type;

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
            JsonObject to = je.getAsJsonObject();
            task = new Task();
            task.setDesignation(to.get("designation").getAsString());
            task.setDuration(to.get("duration").getAsString());
            task.setRest(to.get("rest").getAsString());
            task.setEquipment(to.get("equipment").getAsString());


            Serie[] series = jdc.deserialize(to.get("series"), Serie[].class);
            for(Serie serie: series)
                task.series.add(serie);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return task;
    }
    
}
