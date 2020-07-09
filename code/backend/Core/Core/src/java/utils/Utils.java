package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.*;

import java.util.Collection;
import org.orm.PersistentSession;
import core.User;
import core.UserDAO;
import org.orm.PersistentException;

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

    public static User validateToken(String token, String username, PersistentSession session) throws UserDontExistsException, InvalidTokenException, PersistentException {
        User user;
        if((user = UserDAO.getUserByORMID(session,username)) == null)
            throw new UserDontExistsException(username);
        if(!user.getToken().equals(token))
            throw new InvalidTokenException(token);
        return user;
    }

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
