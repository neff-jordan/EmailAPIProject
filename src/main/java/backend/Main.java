
package backend;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class Main {
    public static void main(String[] args) throws Exception {

        MarsPhoto photoFetcher = new MarsPhoto();
        MarsWeather weatherFetcher = new MarsWeather();
        WriteEmail emailComposer = new WriteEmail();

        String photoData = photoFetcher.getMarsPhoto();
        String weatherData = weatherFetcher.getMarsWeather();
        String emailBody = emailComposer.composeEmail(photoData, weatherData);

        String sendgridApiKey = System.getenv("SENDGRID_API_KEY");
        if (sendgridApiKey == null || sendgridApiKey.isEmpty()) {
            System.err.println("SendGrid API Key is not set!");
            return;
        }

        Email from = new Email("neff.jordan1936@gmail.com");
        String subject = "Daily Mars Status Update";
        Email to = new Email("archeryworldcup@icloud.com");
        Content content = new Content("text/plain", emailBody);
        //Content testContent = new Content("text/plain","hahahaha");
        Mail mail = new Mail(from, subject, to, content);

//        mail.setFrom(from);
//        mail.setSubject(subject);
//        mail.addContent(content);

//        Personalization personalization = new Personalization();
//        personalization.addTo(to);
//        mail.addPersonalization(personalization);

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



