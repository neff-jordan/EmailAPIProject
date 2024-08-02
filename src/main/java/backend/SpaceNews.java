package backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class SpaceNews {

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
                        .append("Published At: ").append(publishedAt).append("\n")
                        .append("\n\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articlesBuilder.toString();
    }

    private String getApiResponse() throws Exception {

        Random rand = new Random();
        int randomPage = rand.nextInt(2100);
        String API_URL = "https://api.spaceflightnewsapi.net/v4/articles/?limit=5&published_at_lte=2024-08-01&offset=" + randomPage;


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}