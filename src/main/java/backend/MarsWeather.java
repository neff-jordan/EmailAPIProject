/**
 * currently not in use because there are no rovers on Mars transmitting current weather data
 */

package backend;

public class MarsWeather {
    private static final String API_KEY = "R3M4DqxBWvtwBTu0sXNVPbJBNqJMQ7igP7CjZuaP";
    private static final String API_URL = "https://api.nasa.gov/insight_weather/?api_key=" + API_KEY + "&feedtype=json&ver=1.0";

    public static void main(String[] args) {
        //
    }

    static String getMarsWeather() throws Exception {
        return null;
    }
}
