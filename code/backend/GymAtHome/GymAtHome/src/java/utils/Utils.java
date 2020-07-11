package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.GymAtHomeException;
import exceptions.JsonKeyInFaultException;
import java.io.IOException;
import java.util.Collection;
import okhttp3.Response;

public class Utils {

    /**
     * Create a json string with the error message and code.
     * 
     * @param code The HTTP code to response.
     * @param msg The error message.
     * @return A json string.
     */
    public static String makeError(int code, String msg) {
        return "{ \"status\": \"error\", " +
                "\"code\": " + code + ", " +
                "\"msg\": \"" + msg + "\", " +
                "\"data\": null " +
                " }";
    }

    /**
     * Create a json string with the data to retrieve.
     *
     * @param code The HTTP code to response.
     * @param data The data to retrieve.
     * @return A json string.
     */
    public static String makeSuccess(int code, String data) {
        return "{ \"status\": \"success\", " +
                "\"code\": " + code + ", " +
                "\"msg\": null, " +
                "\"data\": " + data +
                " }";
    }
    
    /**
     * Validate if all mandatory keys passed on tags are in the json string.
     * 
     * @param gson The gson parser to convert a json string to a JsonObject.
     * @param json The json string with info.
     * @param tags Mandatory keys to check in json string.
     * @return A JsonObject parsed from de json string.
     * @throws JsonKeyInFaultException if some of the mandatory keys is in fault
     */
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
