package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.JsonKeyInFaultException;

import java.util.Collection;

public class Utils {

    public static JsonObject validateJson(Gson gson, String json, Collection<String> tags) throws JsonKeyInFaultException {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        for(String tag: tags) {
            if (!jsonObject.has(tag)) {
                throw new JsonKeyInFaultException(tag);
            }
        }
        return jsonObject;
    }
}
