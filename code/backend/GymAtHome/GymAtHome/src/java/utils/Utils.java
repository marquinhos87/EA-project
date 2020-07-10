package utils;

import exceptions.GymAtHomeException;
import java.io.IOException;
import okhttp3.Response;

public class Utils {

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
    
    /**
     * Check if HTTPCode is different from the code of the Response, if is different throw an Exception with response body.
     * 
     * @param response Response from a remote invoke.
     * @param HTTPCode HTTPCode to test.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public static void testHTTPCode(Response response, int HTTPCode) throws IOException, GymAtHomeException {
        if(response.code()!=HTTPCode) {
            throw new GymAtHomeException(response.body().string());
        }
    }
}
