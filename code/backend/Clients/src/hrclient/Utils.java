package hrclient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.hibernate.Query;
import org.orm.PersistentException;

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

    public static JsonObject validateJson(Gson gson, String json, Collection<String> tags) throws JsonKeyInFaultException {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        for(String tag: tags) {
            if (!jsonObject.has(tag)) {
                throw new JsonKeyInFaultException(tag);
            }
        }
        return jsonObject;
    }

    public static void validateToken(String token, String username, String entity) throws PersistentException, TokenIsInvalidException {
        Query query = HRClientFacade.getSession().createQuery("select token from " + entity + " where token='" + token + "' and username='" + username+"'");
        if(query.list().size() == 0) throw new TokenIsInvalidException(token);
    }
}
