package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import core.*;
import exceptions.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;
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
}
