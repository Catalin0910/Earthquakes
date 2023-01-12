import java.time.LocalDateTime;

public class Earthquake {

    private String country;
    private double magnitude;
    private String location;
    private LocalDateTime timestamp;

    public Earthquake(String country, double magnitude, String location, LocalDateTime timestamp) {
        this.country = country;
        this.magnitude = magnitude;
        this.location = location;
        this.timestamp = timestamp;
    }

    public String getCountry() {
        return country;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
