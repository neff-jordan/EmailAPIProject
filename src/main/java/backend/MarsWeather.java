package backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MarsWeather {
    private static final String API_KEY = "R3M4DqxBWvtwBTu0sXNVPbJBNqJMQ7igP7CjZuaP";
    private static final String API_URL = "https://api.nasa.gov/insight_weather/?api_key=" + API_KEY + "&feedtype=json&ver=1.0";

    public static void main(String[] args) {
        try {
            String response = getMarsWeather();
            JSONObject jsonResponse = new JSONObject(response);

            JSONArray solKeys = jsonResponse.getJSONArray("sol_keys");
            if (solKeys.length() == 0) {
                System.out.println("No valid weather data available.");
                return;
            }

            // Get the most recent sol
            String recentSol = solKeys.getString(solKeys.length() - 1);
            JSONObject solData = jsonResponse.getJSONObject(recentSol);

            // Print weather data for the most recent sol
            System.out.println("Sol: " + recentSol);
            System.out.println("Average temperature: " + solData.getJSONObject("AT").getDouble("av"));
            System.out.println("Minimum temperature: " + solData.getJSONObject("AT").getDouble("mn"));
            System.out.println("Maximum temperature: " + solData.getJSONObject("AT").getDouble("mx"));
            System.out.println("Average wind speed: " + solData.getJSONObject("HWS").getDouble("av"));
            System.out.println("Average pressure: " + solData.getJSONObject("PRE").getDouble("av"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getMarsWeather() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        conn.disconnect();
        return sb.toString();
    }
}
