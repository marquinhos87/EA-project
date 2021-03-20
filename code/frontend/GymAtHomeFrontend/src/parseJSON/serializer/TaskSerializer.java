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
import core.Task;
import java.lang.reflect.Type;

/**
 *
 * @author joaomarques
 */
public class TaskSerializer implements JsonSerializer<Task> {

    /**
     * Serialize a Task to a JsonElement.
     * 
     * @param task The task to serialize.
     * @param type
     * @param jsc
     * @return A JsonElement with a Task inside.
     */
    @Override
    public JsonElement serialize(Task task, Type type, JsonSerializationContext jsc) {
        final JsonObject jsonObj = new JsonObject();
        jsonObj.add("designation",jsc.serialize(task.getDesignation()));
        jsonObj.add("rest", jsc.serialize(task.getRest()));
        jsonObj.add("duration", jsc.serialize(task.getDuration()));
        jsonObj.add("equipment", jsc.serialize(task.getEquipment()));
        jsonObj.add("series", jsc.serialize(task.series.toArray()));
        return jsonObj;
    }
    
}
