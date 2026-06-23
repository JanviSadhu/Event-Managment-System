package model;

public class Event {
	 private int id;
	    private String name;
	    private int maxParticipants;

	    public Event(String name, int maxParticipants) {
	        
	        this.name = name;
	        this.maxParticipants = maxParticipants;
	    }

	    public int getId() { return id; }
	    public String getName() { return name; }
	    public int getMaxParticipants() { return maxParticipants; }

}
