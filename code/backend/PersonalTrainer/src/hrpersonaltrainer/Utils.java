package hrpersonaltrainer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import redis.clients.jedis.Jedis;

import java.util.*;


public class Utils {

    public static String tokenGenerate(String username) {
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

    public static int years(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTime(date);
        return cal;
    }

    public static void validateClientToken(String token, String username, Jedis redis) throws TokenIsInvalidException, ClientNotExistsException {
        if (redis.exists(username) == false) throw new ClientNotExistsException(username);
        String cachedToken = redis.get(username);
        if(cachedToken.equals(token) == false) throw new TokenIsInvalidException(token);
    }

    public static void validatePersonalTrainerToken(String token, String username, Jedis redis) throws TokenIsInvalidException, PersonalTrainerNotExistsException {
        if (redis.exists(username) == false) throw new PersonalTrainerNotExistsException(username);
        String cachedToken = redis.get(username);
        if(cachedToken.equals(token) == false) throw new TokenIsInvalidException(token);
    }

}
