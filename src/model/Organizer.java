package model;

import java.util.ArrayList;
import java.util.List;

public class Organizer {

    private int organizerId;
    private String name;
    private String email;
    private String phone;
    private List<Event> events;

    public Organizer(int organizerId, String name, String email, String phone) {
        this.organizerId = organizerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.events = new ArrayList<>();
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public List<Event> getEvents() {
        return events;
    }

    // Getters & Setters
    public int getOrganizerId() { return organizerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Organizer{" +
                "id=" + organizerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", events=" + events.size() +
                '}';
    }
}

