
package backend;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.example.MySQLConnection;

import java.util.List;

public class SendEmail {


    public static void main(String[] args) throws Exception {
        SendEmail send = new SendEmail();
        send.sendingEmail();
    }

    public void sendingEmail() throws Exception {

        MarsPhoto photoFetcher = new MarsPhoto();
        //MarsWeather weatherFetcher = new MarsWeather();
        //LaunchObjects launchObjects = new LaunchObjects();
        SpaceNews spaceNews = new SpaceNews();
        WriteEmail emailComposer = new WriteEmail();

        String photoUrl = photoFetcher.getMarsPhoto();
        //String weatherData = weatherFetcher.getMarsWeather();
        //String launchData = launchObjects.getLaunchObjects();
        String news = spaceNews.fetchAndReturnArticles();
        String emailBody = emailComposer.composeEmail(photoUrl, news);

        String sendgridApiKey = System.getenv("SENDGRID_API_KEY");
        if (sendgridApiKey == null || sendgridApiKey.isEmpty()) {
            System.err.println("SendGrid API Key is not set!");
            return;
        }

        Email from = new Email("neff.jordan1936@gmail.com");
        String subject = "Daily Space Industry News";

        // create a for loop that iterates through the list of subscribed users
        MySQLConnection connection = MySQLConnection.getInstance();
        List<String> usersEmail = connection.getAllSubscribedUsersEmails();

        for(String email : usersEmail) {

            Email to = new Email(email);

            Content content = new Content("text/html", emailBody); // Changed to HTML content
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(sendgridApiKey);
            Request request = new Request();

            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println("Response Code: " + response.getStatusCode());
                System.out.println("Response Body: " + response.getBody());
                System.out.println("Response Headers: " + response.getHeaders());

                if (response.getStatusCode() == 202) {
                    System.out.println("Email sent successfully!");
                } else {
                    System.err.println("Failed to send email. Response Code: " + response.getStatusCode());
                    System.err.println("Response Body: " + response.getBody());
                }
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }
        }

    }
}
