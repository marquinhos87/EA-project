package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.JsonKeyInFaultException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

public class Utils {
    public static final String PROTOCOL = "http";

    //public static final String SERVER_URL = "192.168.1.139";

    public static final String SERVER_URL = "192.168.1.53";

    public static final String SERVER_PORT = "8081";
    public static final String SERVER_CONTROLLER = "GymAtHome";
    public static final String SERVER = PROTOCOL + "://" + SERVER_URL + ":" + SERVER_PORT + "/" + SERVER_CONTROLLER + "/api/";

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        byte[] arr = password.getBytes();
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
        shaDigest.reset();
        //Read file data and update in message digest
        shaDigest.update(arr, 0, arr.length);
        //Get the hash’s bytes
        byte[] bytes = shaDigest.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<bytes.length; i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //return complete hash
        return sb.toString();
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String path, String page, String action) throws ServletException, IOException {
        request.setAttribute("action", action);
        request.setAttribute("page", page);
        request.getRequestDispatcher(path).forward(request, response);
    }

    public static JsonObject validateJson(Gson gson, String json, Collection<String> tags) throws JsonKeyInFaultException {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        System.err.println("SERVER_RESPONSE:" + json);
        for(String tag: tags) {
            if (!jsonObject.has(tag)) {
                throw new JsonKeyInFaultException(tag);
            }
        }
        return jsonObject;
    }
}
