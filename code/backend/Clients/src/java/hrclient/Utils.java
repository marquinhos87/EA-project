package hrclient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.hibernate.Query;
import org.orm.PersistentException;

import java.util.*;
import org.orm.PersistentSession;

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
        return "{\"service\": \"client\", " + 
                "\"status\": \"success\", " +
                "\"code\": " + code + ", " +
                "\"msg\": null, " +
                "\"data\": " + data +
                " }";
    }

    public static String makeError(int code, String msg) {
        return "{\"service\": \"client\", " + 
                "\"status\": \"error\", " +
                "\"code\": " + code + ", " +
                "\"msg\": \"" + msg + "\", " +
                "\"data\": null " +
                " }";
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

    public static JsonObject validateJson(Gson gson, String json, Collection<String> tags) throws JsonKeyInFaultException {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        for(String tag: tags) {
            if (!jsonObject.has(tag)) {
                throw new JsonKeyInFaultException(tag);
            }
        }
        return jsonObject;
    }

    public static User validateUserToken(String token, String username, PersistentSession session) throws TokenIsInvalidException, UserDoesNotExistException, PersistentException {
        User user;
        if((user = UserDAO.getUserByORMID(session, username)) == null) throw new UserDoesNotExistException(username);
        String cachedToken = user.getToken();
        if(cachedToken.equals(token) == false) throw new TokenIsInvalidException(token);
        return user;
    }
    
    public static boolean registerExists(String key, String value,  String table, PersistentSession session) throws PersistentException{
        return session.createQuery("select " + key + " from " + table + " where " + key + "=\'" + value + "\'").list().size() == 1;
    }
}
