package backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MarsPhoto {
    private static final String NASA_API_KEY = "DUXawtyz6CSw9lMIt70nbs46h3iWFugsZUGa0Y7X";
    private static final String NASA_API_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=" + NASA_API_KEY;
    public String getMarsPhoto() throws Exception {

        URL url = new URL(NASA_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return content.toString();

    }

}
