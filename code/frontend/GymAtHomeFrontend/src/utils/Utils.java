package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.JsonKeyInFaultException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

public class Utils {
    public static final String PROTOCOL = "http";

    //public static final String SERVER_URL = "gymathome";
    public static final String SERVER_URL = "localhost";

<<<<<<< HEAD
    public static final String SERVER_PORT = "8080";
=======
    //public static final String SERVER_PORT = "8080";
    public static final String SERVER_PORT = "8081";
>>>>>>> 233850906b45d90e5dbd25e9cb7045cbb8cce03e
    public static final String SERVER_CONTROLLER = "GymAtHome";
    public static final String SERVER = PROTOCOL + "://" + SERVER_URL + ":" + SERVER_PORT + "/" + SERVER_CONTROLLER + "/api/";

    public static final String CONNECTION_LOST_MSG = "A ligação ao servidor foi perdida! Por favor tente recarregar a página. Caso não funcione, contacte o apio ao cliente.";
    public static final String UNEXPECTED_ERROR_MSG = "Erro interno! Por favor tente recarregar a página. Caso o erro persista contacte o apoio ao cliente.";

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

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + path);
    }

    public static JsonObject validateJson(Gson gson, String json, Collection<String> tags) throws JsonKeyInFaultException {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        System.err.println(json);
        for(String tag: tags) {
            if (!jsonObject.has(tag)) {
                throw new JsonKeyInFaultException(tag);
            }
        }
        return jsonObject;
    }

    public static String prettyPrintWeekDays(String weekDays) {
        String[] subtr = weekDays.split(";");
        StringBuilder sb = new StringBuilder("");
        for(int j = 0; j < subtr.length; j++) {
            switch (subtr[j]) {
                case "1":
                    sb.append("Seg");
                    break;
                case "2":
                    sb.append("Ter");
                    break;
                case "3":
                    sb.append("Qua");
                    break;
                case "4":
                    sb.append("Qui");
                    break;
                case "5":
                    sb.append("Sex");
                    break;
                case "6":
                    sb.append("Sáb");
                    break;
                case "7":
                    sb.append("Dom");
                    break;
                default:
                    break;
            }
            if (j == subtr.length - 2)
                sb.append(" e ");
            else if (j != subtr.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }

    public static String prettyPrintWeekDay(int day) {
        switch (day) {
            case 1:
                return "Segunda";
            case 2:
                return "Terça";
            case 3:
                return "Quarta";
            case 4:
                return "Quinta";
            case 5:
                return "Sexta";
            case 6:
                return "Sábado";
            case 7:
                return "Domingo";
            default:
                return "---";
        }
    }

    public static String prettyPrintLevel(int level) {
        switch(level){
            case 0:
                return "Fácil";
            case 1:
                return "Normal";
            case 2:
                return "Díficil";
            case 3:
                return "Extremo";
            default:
                return "---";
        }
    }

    public static String prettyPrintGenre(String sex) {
        switch(sex){
            case "m":
                return "Masculino";
            case "f":
                return "Feminino";
            case "o":
                return "Outro";
            default:
                return "---";
        }
    }
}