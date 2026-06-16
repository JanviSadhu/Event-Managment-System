/**
 * Thrown when an attempt is made to register for an event
 * that has already reached its maximum capacity.
 */
public class EventFullException extends Exception {

    private final String eventId;

    public EventFullException(String eventId) {
        super("Event '" + eventId + "' is full – no available spots remaining.");
        this.eventId = eventId;
    }

    public EventFullException(String eventId, String message) {
        super(message);
        this.eventId = eventId;
    }

    /** Returns the ID of the event that triggered this exception. */
    public String getEventId() {
        return eventId;
    }
}
