package utils;

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
}
