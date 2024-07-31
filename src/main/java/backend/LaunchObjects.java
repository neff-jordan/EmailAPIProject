package backend;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LaunchObjects {
    private String apiDestination = "https://api.spaceflightnewsapi.net/v4/articles/?title_contains=SpaceX&published_at_lte=2024-01-01";
    public String getLaunchObjects() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // GET request
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(apiDestination))
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
