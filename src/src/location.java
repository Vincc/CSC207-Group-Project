package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

public class location {

    private static final String my_key = "3ab5f29554644266926738b598102edc";
    private static final String url = "https://api.opencagedata.com/geocode/v1/json";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Latitude:");
        double latitude = in.nextDouble();
        System.out.println("Enter Longitude:");
        double longitude = in.nextDouble();
//        double latitude = 50.976004;
//        double longitude = 11.336753;

        try {
            String html_response = reverseGeoLocation(latitude, longitude);
            String response_city = extract_City(html_response);
            String response_country = extract_Country(html_response);
            System.out.println(response_city + ", " + response_country);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String reverseGeoLocation(double latitude, double longitude) throws IOException {
        String urlStr = url + "?q=" + latitude + "," + longitude + "&key=" + my_key + "&abbrv:1";
        URL url = new URL(urlStr);

        HttpURLConnection httpconnection = (HttpURLConnection) url.openConnection();
        httpconnection.setRequestMethod("GET");

        int response = httpconnection.getResponseCode();

        if (response == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpconnection.getInputStream()))) {
                return in.lines().collect(Collectors.joining());
            }
        } else {
            throw new IOException("Error: " + response);
        }

    }
    public static String extract_City(String response){
        String Almost =  response.substring(response.indexOf("city") ,response.indexOf("continent"));
        return  Almost.replace(",","");
    }
    public static String extract_Country(String response) {
        String Almost = response.substring(response.indexOf("country"), response.indexOf("country_code"));
        return Almost.replace(",", "");
    }
}
