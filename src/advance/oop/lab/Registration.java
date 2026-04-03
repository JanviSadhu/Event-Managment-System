package advance.oop.lab;

public class Registration {
	private int registrationId;
    private Event event;
    private Participants participant;
    private String registrationDate;

    public Registration() {}

    public Registration(int registrationId, Event event, Participants participant, String registrationDate) {
        this.registrationId = registrationId;
        this.event = event;
        this.participant = participant;
        this.registrationDate = registrationDate;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participants getParticipant() {
        return participants;
    }

    public void setParticipant(Participants participant) {
        this.participants = participant;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    // toString Method
    @Override
    public String toString() {
        return "Registration ID: " + registrationId +
               ", Participant: " + participant.getName() +
               ", Event: " + event.getName() +
               ", Date: " + registrationDate;
    }

}
