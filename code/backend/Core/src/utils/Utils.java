package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import core.*;
import exceptions.InvalidTokenException;
import exceptions.JsonKeyInFaultException;
import exceptions.PersonalTrainerDontExistsException;
import exceptions.UserTokenDontExistsException;
import org.orm.PersistentException;
import org.orm.PersistentSession;

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

    public static UserToken validateToken(PersistentSession session, String username, String token) throws UserTokenDontExistsException, InvalidTokenException, PersistentException {
        UserToken ut;
        if ((ut = UserTokenDAO.getUserTokenByORMID(session,username)) == null)
            throw new UserTokenDontExistsException(username);
        if (!ut.getToken().equals(token))
            throw new InvalidTokenException(token);
        return ut;
    }
}
