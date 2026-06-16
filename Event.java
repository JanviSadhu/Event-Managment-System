/**
 * Represents an event in the system.
 */
public class Event {

    private String eventId;
    private String name;
    private String date;       // e.g. "2025-09-15"
    private String location;
    private int capacity;      // maximum number of attendees
    private int registeredCount; // current number of registrations

    // ----------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------
    public Event(String eventId, String name, String date, String location, int capacity) {
        this.eventId         = eventId;
        this.name            = name;
        this.date            = date;
        this.location        = location;
        this.capacity        = capacity;
        this.registeredCount = 0;
    }

    // ----------------------------------------------------------------
    // Capacity helpers
    // ----------------------------------------------------------------

    /** Returns true when no more registrations are possible. */
    public boolean isFull() {
        return registeredCount >= capacity;
    }

    /** Remaining spots available. */
    public int availableSpots() {
        return capacity - registeredCount;
    }

    /** Increments the registration counter (called by EventController). */
    public void incrementRegistered() {
        registeredCount++;
    }

    /** Decrements the registration counter (called on cancellation). */
    public void decrementRegistered() {
        if (registeredCount > 0) {
            registeredCount--;
        }
    }

    // ----------------------------------------------------------------
    // Getters & Setters
    // ----------------------------------------------------------------
    public String getEventId()          { return eventId; }
    public String getName()             { return name; }
    public void   setName(String name)  { this.name = name; }
    public String getDate()             { return date; }
    public void   setDate(String date)  { this.date = date; }
    public String getLocation()         { return location; }
    public void   setLocation(String l) { this.location = l; }
    public int    getCapacity()         { return capacity; }
    public void   setCapacity(int c)    { this.capacity = c; }
    public int    getRegisteredCount()  { return registeredCount; }

    // ----------------------------------------------------------------
    // toString
    // ----------------------------------------------------------------
    @Override
    public String toString() {
        return String.format(
            "Event[id=%s, name='%s', date=%s, location='%s', capacity=%d, registered=%d]",
            eventId, name, date, location, capacity, registeredCount
        );
    }
}
