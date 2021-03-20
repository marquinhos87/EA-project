package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Collection;
import org.orm.PersistentSession;
import org.orm.PersistentException;

import exceptions.*;
import core.User;
import core.UserDAO;

public class Utils {

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

    /**
     * Validate the token for the given user.
     * 
     * @param token The token to validate user identity.
     * @param username The user username.
     * @param session PersistentSession to access database.
     * @return An User.
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws PersistentException if some error occur with hibernate
     */
    public static User validateToken(String token, String username, PersistentSession session) throws UserDontExistsException, InvalidTokenException, PersistentException {
        User user;
        if((user = UserDAO.getUserByORMID(session,username)) == null)
            throw new UserDontExistsException(username);
        if(!user.getToken().equals(token))
            throw new InvalidTokenException(token);
        return user;
    }

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
}
