import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class location {

    private static final String my_key = "3ab5f29554644266926738b598102edc";
    private static final String url = "https://api.opencagedata.com/geocode/v1/json";

    public static void main(String[] args) {
        double latitude = 50.976004;
        double longitude = 11.336753;

        try {
            String response = reverseGeocodeLocation(latitude, longitude);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String reverseGeocodeLocation(double latitude, double longitude) throws IOException {
        String urlStr = url + "?q=" + latitude + "," + longitude + "&key=" + my_key + "&key=address:1";
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                return in.lines().collect(Collectors.joining());
            }
        } else {
            throw new IOException("Error: " + responseCode);
        }

    }
}
