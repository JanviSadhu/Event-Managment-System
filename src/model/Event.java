package model;
import java.time.LocalDate;


public class Event {


	    private int eventId;
	    private String name;
	    private String location;
	    private LocalDate date;
	    private int maxParticipants;
	    private int currentParticipants;

	    public Event(int eventId, String name, String location, LocalDate date, int maxParticipants) {
	        this.eventId = eventId;
	        this.name = name;
	        this.location = location;
	        this.date = date;
	        this.maxParticipants = maxParticipants;
	        this.currentParticipants = 0;
	    }

	    public boolean isFull() {
	        return currentParticipants >= maxParticipants;
	    }

	    public void addParticipant() {
	        if (!isFull()) {
	            currentParticipants++;
	        }
	    }

	    // Getters & Setters
	    public int getEventId() { return eventId; }
	    public String getName() { return name; }
	    public String getLocation() { return location; }
	    public LocalDate getDate() { return date; }
	    public int getMaxParticipants() { return maxParticipants; }

	    public void setName(String name) { this.name = name; }
	    public void setLocation(String location) { this.location = location; }
	    public void setDate(LocalDate date) { this.date = date; }

	    @Override
	    public String toString() {
	        return "Event{" +
	                "id=" + eventId +
	                ", name='" + name + '\'' +
	                ", location='" + location + '\'' +
	                ", date=" + date +
	                ", maxParticipants=" + maxParticipants +
	                ", currentParticipants=" + currentParticipants +
	                '}';
	    }
	}

