package backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MarsPhoto {
    private static final String NASA_API_KEY = "DUXawtyz6CSw9lMIt70nbs46h3iWFugsZUGa0Y7X";
    private static final String NASA_API_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/latest_photos?api_key=" + NASA_API_KEY;

    public String getMarsPhoto() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // GET request
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(NASA_API_URL))
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        // Parse JSON response
        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray latestPhotos = jsonObject.getJSONArray("latest_photos");

        // Extract the first photo URL (you can change this logic as needed)
        if (latestPhotos.length() > 0) {
            JSONObject firstPhoto = latestPhotos.getJSONObject(0);
            String imgSrc = firstPhoto.getString("img_src");
            return imgSrc;
        } else {
            throw new Exception("No photos found");
        }
    }
}
