/**
 * This class is responsible for composing an HTML-formatted email that
 * includes a Mars photo and space news articles.
 */

package backend;

public class WriteEmail {

    /**
     * Composes an HTML email containing a Mars photo and a list of space news articles.
     *
     * @param photoUrl The URL of the Mars photo to be included in the email.
     * @param news A formatted string containing space news articles, where each
     *             article is separated by two newline characters ("\n\n").
     * @return A string containing the complete HTML body of the email.
     */
    public String composeEmail(String photoUrl, String news) {

        // Split news into individual stories using a delimiter (e.g., "\n\n")
        String[] stories = news.split("\n\n");

        // Create a StringBuilder for the email body
        StringBuilder emailBody = new StringBuilder();

        emailBody.append("<html>")
                .append("<body>")
                .append("<h1>Daily Space Industry News</h1>")
                .append("<h2>Photo</h2>")
                .append("<img src=\"").append(photoUrl).append("\" alt=\"Mars Photo\" style=\"max-width: 100%; height: auto;\"/>")
                .append("<h2>News</h2>");

        // Append each story as a paragraph
        for (String story : stories) {
            emailBody.append("<p>").append(story).append("</p>");
        }

        emailBody.append("</body>")
                .append("</html>");

        return emailBody.toString();
    }
}
