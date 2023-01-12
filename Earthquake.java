import java.time.LocalDateTime;

public class Earthquake {

    private double magnitude;
    private String location;
    private LocalDateTime timestamp;

    public Earthquake(double magnitude, String location, LocalDateTime timestamp) {
        this.magnitude = magnitude;
        this.location = location;
        this.timestamp = timestamp;
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
