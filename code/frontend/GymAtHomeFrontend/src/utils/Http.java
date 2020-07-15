package utils;

import okhttp3.*;

import java.io.IOException;

public class Http {
    private static OkHttpClient client = new OkHttpClient();

    /**
     * Given a Url of a service execute a HTTP get and return the response.
     *
     * @param url Url from the service to access.
     * @return Response to the Request.
     * @throws IOException
     */
    public static Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("content-type", "application/JSON")
                .build();

        return client.newCall(request).execute();
    }

    /**
     * Given a Url of a service execute a HTTP post and return the response.
     *
     * @param url Url from the service to access.
     * @param jsonContent Json Content to send on the Request.
     * @return Response to the Request.
     * @throws IOException
     */
    public static Response post(String url, String jsonContent) throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonContent,JSON);
        System.err.println("[METHOD=POST]:" + url);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/JSON")
                .build();

        return client.newCall(request).execute();
    }
}