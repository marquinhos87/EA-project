package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class Utils {

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

    public static String JsonToURLParameters(String json) throws UnsupportedEncodingException {
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        StringBuilder sb = new StringBuilder();
        Set<String> keys = jsonObject.keySet();
        for(String key: keys) {
            sb.append(key + "=" + jsonObject.get(key).getAsString() + "&");
        }
        String parameters = sb.toString();
        return URLEncoder.encode(parameters.substring(0,parameters.length()-1), String.valueOf(StandardCharsets.UTF_8));
    }

    public static String parametersToJSON(Map<String,String[]> parameters) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int i = parameters.size();
        for(Map.Entry<String,String[]> parameter: parameters.entrySet()) {
            i--;
            if(i == 0)
                sb.append("\"" + parameter.getKey() + "\":\"" + parameter.getValue()[0] + "\"");
            else
                sb.append("\"" + parameter.getKey() + "\":\"" + parameter.getValue()[0] + "\",");
        }
        sb.append("}");
        return sb.toString();
    }
}
