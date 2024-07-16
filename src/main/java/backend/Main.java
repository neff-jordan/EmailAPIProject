package backend;

public class Main {
    public static void main(String[] args) {
        // Create an instance of SendEmail with SMTP server details
        SendEmail emailSender = new SendEmail(
                "neff.jordan1936@gmail.com",
                "smtp.gmail.com", 587, // SMTP server details
                "neff.jordan1936@gmail.com", // SMTP username (typically same as email)
                System.getenv("EMAIL_PASSWORD") // SMTP password from environment variable
        );

        // Send an email
        emailSender.sendEmail("archeryworldcup@icloud.com", "Daily Mars Status Update", "This is the body of the email.");
    }
}
