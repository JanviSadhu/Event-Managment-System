package advance.oop.lab;

public class Participants {
	 private int participantId;
	    private String name;
	    private String email;

	    // Default Constructor
	    public Participants() {}

	    // Parameterized Constructor
	    public Participants(int participantId, String name, String email) {
	        this.participantId = participantId;
	        this.name = name;
	        this.email = email;
	    }

	    // Getters and Setters
	    public int getParticipantId() {
	        return participantId;
	    }

	    public void setParticipantId(int participantId) {
	        this.participantId = participantId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    // toString Method
	    @Override
	    public String toString() {
	        return "Participant ID: " + participantId +
	               ", Name: " + name +
	               ", Email: " + email;
	    }

}
