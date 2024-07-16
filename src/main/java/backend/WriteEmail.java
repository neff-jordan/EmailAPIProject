package backend;

public class WriteEmail {
    public String composeEmail(String photoData, String weatherData) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Mars Daily Status Report\n\n");
        emailBody.append("Photo Data:\n").append(photoData).append("\n\n");
        emailBody.append("Weather Data:\n").append(weatherData).append("\n");

        return emailBody.toString();
    }
}
