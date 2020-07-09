/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseJSON.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import core.Serie;
import core.Task;
import core.Week;
import core.Workout;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 *
 * @author joaomarques
 */
public class WeekDeserializer implements JsonDeserializer<Week> {

    @Override
    public Week deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Week week = null;
        try {
            JsonObject jo = je.getAsJsonObject();
            week = new Week();
            week.setNumber(jo.get("number").getAsInt());
            String initialDate = jo.get("initialDate").getAsString();
            week.setInitialDate(new SimpleDateFormat("yyyy-MM-dd").parse(initialDate));
            String finalDate = jo.get("finalDate").getAsString();
            week.setFinalDate(new SimpleDateFormat("yyyy-MM-dd").parse(finalDate));
            
            Workout[] workouts = jdc.deserialize(jo.get("workouts"), Workout[].class);
            for(Workout workout: workouts)
                week.workouts.add(workout);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return week;
    }
    
}
