/**
 * This class handles fetching a random Mars photo from the NASA Mars Rover API.
 */

package backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class MarsPhoto {

    private final String NASA_API_KEY = "DUXawtyz6CSw9lMIt70nbs46h3iWFugsZUGa0Y7X";

    /**
     * Fetches a random photo from the Mars Rover API by selecting a random sol (Martian day).
     *
     * @return A String containing the URL of the first photo returned by the API.
     *
     * @throws Exception
     */
    public String getMarsPhoto() throws Exception {

        Random rand = new Random();
        // there are about 3900 sols (days) of photos
        int random = rand.nextInt(3900);
        String NASA_API_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=" + random + "&api_key=" + NASA_API_KEY;


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
        JSONArray latestPhotos = jsonObject.getJSONArray("photos");

        // Extract the first photo URL
        if (latestPhotos.length() > 0) {
            JSONObject firstPhoto = latestPhotos.getJSONObject(0);
            String imgSrc = firstPhoto.getString("img_src");
            return imgSrc;
        } else {
            throw new Exception("No photos found");
        }
    }
}
