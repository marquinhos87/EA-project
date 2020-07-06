package hrclient;

import com.google.gson.JsonObject;

import java.util.Collection;
import java.util.Random;

public class Utils {
    protected static String tokenGenerate(String username) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 32 - username.length()) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return username + salt.toString();
    }

    public static String makeSuccess200(String data) {
        return makeSuccess(200, data);
    }

    public static String makeSuccess(int code, String data) {
        return "{ \"status\": \"success\", " +
                "\"code\": " + code + ", " +
                "\"msg\": null, " +
                "\"data\": " + data +
                " }";
    }

    public static String makeError(int code, String msg) {
        return "{ \"status\": \"error\", " +
                "\"code\": " + code + ", " +
                "\"msg\": \"" + msg + "\", " +
                "\"data\": null " +
                " }";
    }

    public static JsonObject validateJson(Collection<String> tags, JsonObject json) throws JsonKeyInFaultException {
        for(String tag: tags) {
            if (!json.has(tag)) {
                throw new JsonKeyInFaultException(tag);
            }
        }
        return json;
    }
}
