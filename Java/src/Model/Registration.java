package Model;

public class Registration {

    private int id;
    private Event event;
    private Participant participant;

    public Registration() {}


    public Registration(Event event, Participant participant) {
        this.event = event;
        this.participant = participant;
    }

    public Event getEvent() {
        return event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}