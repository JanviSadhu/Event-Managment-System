package Model;

public class Event {

    private int id;
    private String name;
    private String location;
    private String date;
    private int maxParticipants;

    // ✅ DEFAULT CONSTRUCTOR (REQUIRED FOR DAO)
    public Event() {
    }

    // Constructor with all fields
    public Event(int id, String name, String location, String date, int maxParticipants) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.maxParticipants = maxParticipants;
    }

    // ======================
    // GETTERS
    // ======================
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    // ======================
    // SETTERS (IMPORTANT)
    // ======================
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", maxParticipants=" + maxParticipants +
                '}';
    }
}