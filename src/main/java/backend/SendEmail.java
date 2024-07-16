package backend;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.*;
import org.simplejavamail.mailer.MailerBuilder;

public class SendEmail {
    private final String emailOrigination;
    private final String smtpServer;
    private final int smtpPort;
    private final String smtpUsername;
    private final String smtpPassword;

    public SendEmail(String emailOrigination, String smtpServer, int smtpPort, String smtpUsername, String smtpPassword) {
        this.emailOrigination = emailOrigination;
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
        this.smtpUsername = smtpUsername;
        this.smtpPassword = smtpPassword;
    }

    public void sendEmail(String emailDestination, String subject, String body) {
        // Create the email
        Email email = EmailBuilder.startingBlank()
                .from(emailOrigination)
                .to(emailDestination)
                .withSubject(subject)
                .withPlainText(body)
                .buildEmail();

        // Create the mailer
        Mailer mailer = MailerBuilder
                .withSMTPServer(smtpServer, smtpPort, smtpUsername, smtpPassword)
                .buildMailer();

        // Send the email
        mailer.sendMail(email);

        System.out.println("Email sent successfully!");
    }
}
