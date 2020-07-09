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
import core.Week;
import java.lang.reflect.Type;

/**
 *
 * @author joaomarques
 */
public class WeekSerializer implements JsonSerializer<Week>{
    
    @Override
    public JsonElement serialize(Week week, Type type, JsonSerializationContext context) {
        final JsonObject jsonObj = new JsonObject();
        jsonObj.add("number",context.serialize(week.getNumber()));
        jsonObj.add("initialDate",context.serialize(week.getInitialDate()));
        jsonObj.add("finalDate",context.serialize(week.getFinalDate()));
        jsonObj.add("workouts",context.serialize(week.workouts));
        return jsonObj;
    }
}
