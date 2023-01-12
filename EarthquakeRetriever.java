import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class EarthquakeRetriever {
    public ArrayList<Earthquake> getEarthquakes() throws IOException {
        String urlString = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2022-01-01&endtime=2022-01-02";

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            System.out.println("Error: " + responseCode);
            return new ArrayList<Earthquake>();
        }
        Scanner scanner = new Scanner(url.openStream());
        String response = "";
        while (scanner.hasNext()) {
            response += scanner.nextLine();
        }
        scanner.close();
        JSONObject json = new JSONObject(response);
        JSONArray features = json.getJSONArray("features");
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            JSONObject properties = feature.getJSONObject("properties");
            double magnitude = properties.getDouble("mag");
            String location = properties.optString("place");
            if(location!=null){
                int index = location.indexOf(",");
                if(index != -1)
                    location = location.substring(0,index);
            }
            long timeInMilliseconds = properties.getLong("time");
            LocalDateTime timestamp = LocalDateTime.ofEpochSecond(timeInMilliseconds / 1000,1000, ZoneOffset.UTC);
            earthquakes.add(new Earthquake(magnitude, location, timestamp));
        }
        return earthquakes;
    }

    public ArrayList<Earthquake> getEarthquakes2(String countryCode, int days) throws IOException {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime xDaysAgo = currentDate.minusDays(days);
        String xDaysAgoStr = xDaysAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String urlString = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + xDaysAgoStr + "&country=" + countryCode;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            System.out.println("Error: " + responseCode);
            return new ArrayList<Earthquake>();
        }
        Scanner scanner = new Scanner(url.openStream());
        String response = "";
        while (scanner.hasNext()) {
            response += scanner.nextLine();
        }
        scanner.close();
        JSONObject json = new JSONObject(response);
        JSONArray features = json.getJSONArray("features");
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            JSONObject properties = feature.getJSONObject("properties");
            double magnitude = properties.getDouble("mag");
            String location = properties.getString("place").substring(0, properties.getString("place").indexOf(","));
            long timeInMilliseconds = properties.getLong("time");
            LocalDateTime timestamp = LocalDateTime.ofEpochSecond(timeInMilliseconds / 1000, 1000, ZoneOffset.UTC);
            earthquakes.add(new Earthquake(magnitude, location, timestamp));
        }
        return earthquakes;
    }
}
