package utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static final String PROTOCOL = "http";
    public static final String SERVER_URL = "192.168.1.139";
    public static final String SERVER_PORT = "8081";
    public static final String SERVER_CONTROLLER = "GymAtHome";
    public static final String SERVER = PROTOCOL + "://" + SERVER_URL + ":" + SERVER_PORT + "/" + SERVER_CONTROLLER + "/api/";

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String path, String page, String action) throws ServletException, IOException {
        request.setAttribute("action", action);
        request.setAttribute("page", page);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
