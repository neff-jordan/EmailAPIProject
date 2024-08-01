package backend;

public class WriteEmail {
    public String composeEmail(String photoUrl, String news) {
        return "<html>" +
                "<body>" +
                "<h1>Daily Mars Status Update</h1>" +
                "<h2>Photo</h2>" +
                "<img src=\"" + photoUrl + "\" alt=\"Mars Photo\" style=\"max-width: 100%; height: auto;\"/>" +
                "<h2>News</h2>" +
                "<p>" + news + "</p>" +
                //"<img src=\"" + weatherData + "\" alt=\"Mars Photo\" style=\"max-width: 100%; height: auto;\"/>" +
                "</body>" +
                "</html>";
    }
}
