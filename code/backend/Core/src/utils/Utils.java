package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.*;
import redis.clients.jedis.Jedis;

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

    public static void validateToken(String token, String username, Jedis jedis) throws UserDontExistsException, InvalidTokenException {
        if(!jedis.exists(username)) throw new UserDontExistsException(username);
        if(!jedis.get(username).equals(token)) throw new InvalidTokenException(token);
    }

    /*public static String parametersToJSON(Map<String,String[]> parameters) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int i = parameters.size();
        for(Map.Entry<String,String[]> parameter: parameters.entrySet()) {
            i--;
            if(i == 0)
                sb.append("\"" + parameter.getKey() + "\":\"" + parameter.getValue()[0] + "\"");
            else
                sb.append("\"" + parameter.getKey() + "\":\"" + parameter.getValue()[0] + "\",");
        }
        sb.append("}");
        return sb.toString();
    }*/

    public static String makeError(int code, String msg) {
        return "{ \"status\": \"error\", " +
                "\"code\": " + code + ", " +
                "\"msg\": \"" + msg + "\", " +
                "\"data\": null " +
                " }";
    }

    public static String makeSuccess(int code, String data) {
        return "{ \"status\": \"success\", " +
                "\"code\": " + code + ", " +
                "\"msg\": null, " +
                "\"data\": " + data +
                " }";
    }
}
