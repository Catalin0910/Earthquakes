import java.io.IOException;
import java.util.ArrayList;

public class SecondMain {
    public static void main(String[] args) throws IOException {
        int x = 7;
        String countryCode = "US";
        EarthquakeRetriever retriever = new EarthquakeRetriever();
        try {
            ArrayList<Earthquake> earthquakes = retriever.getEarthquakes2(countryCode, x);
            if (earthquakes.isEmpty()) {
                System.out.println("No Earthquakes were recorded past " + x + " days");
            } else {
                for (Earthquake earthquake : earthquakes) {
                    System.out.println("Magnitude: " + earthquake.getMagnitude());
                    System.out.println("Location: " + earthquake.getLocation());
                    System.out.println("Timestamp: " + earthquake.getTimestamp());
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving the earthquake data: " + e.getMessage());
        }
    }
}
