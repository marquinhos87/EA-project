import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Http {

    public static HttpResponse<String> post(HttpClient client, String url, String json) throws IOException, InterruptedException {

        // cria o HTTP Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // executa o Request
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> get(HttpClient client, String url) throws IOException, InterruptedException {

        // cria o HTTP Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        // executa o Request
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}

/*
    private HttpClient client;
    
    // post
    HttpResponse<String> response = Http.post(client, url, json);
    printNotification(response.body());
    
    // get
    HttpResponse<String> response = Http.get(client, url);
    printNotification(response.body());
*/