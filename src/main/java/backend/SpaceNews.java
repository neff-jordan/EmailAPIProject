package backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpaceNews {
                                            // auto update with the current date + make response only 3-5 articles
    private static final String API_URL = "https://api.spaceflightnewsapi.net/v4/articles/?limit=10&published_at_lte=2024-07-01&title_contains=SpaceX";

    public static void main(String[] args) {
        SpaceNews spaceNews = new SpaceNews();
        String articles = spaceNews.fetchAndReturnArticles();
        System.out.println(articles);
    }

    public String fetchAndReturnArticles() {
        StringBuilder articlesBuilder = new StringBuilder();
        try {
            String response = getApiResponse();
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray articles = jsonResponse.getJSONArray("results");

            for (int i = 0; i < articles.length(); i++) {

                // Need to figure out how to segment all the stories

                JSONObject article = articles.getJSONObject(i);
                String title = article.getString("title");
                String url = article.getString("url");
                String summary = article.getString("summary");
                String publishedAt = article.getString("published_at");


                articlesBuilder.append("Title: ").append(title).append("\n")
                        .append("URL: ").append(url).append("\n")
                        .append("Summary: ").append(summary).append("\n")
                        .append("Published At: ").append(publishedAt).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articlesBuilder.toString();
    }

    private String getApiResponse() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
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
