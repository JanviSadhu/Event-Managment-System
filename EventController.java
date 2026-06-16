import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * EventController
 *
 * Manages the lifecycle of Event objects and exposes the core business
 * operations needed by the rest of the application.
 *
 * Person 1 owns this file.
 */
public class EventController {

    // ----------------------------------------------------------------
    // Internal store  –  single source of truth for all events
    // ----------------------------------------------------------------
    private final ArrayList<Event> events = new ArrayList<>();

    // ----------------------------------------------------------------
    // 1.  createEvent
    // ----------------------------------------------------------------
    /**
     * Creates a new Event and adds it to the store.
     *
     * @param eventId  Unique identifier (e.g. "EVT-001")
     * @param name     Human-readable event name
     * @param date     Date string (e.g. "2025-09-15")
     * @param location Venue / room
     * @param capacity Maximum number of attendees (must be > 0)
     * @return the newly created Event
     * @throws IllegalArgumentException if capacity ≤ 0 or eventId already exists
     */
    public Event createEvent(String eventId, String name,
                             String date, String location, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        if (findById(eventId).isPresent()) {
            throw new IllegalArgumentException("An event with id '" + eventId + "' already exists.");
        }

        Event event = new Event(eventId, name, date, location, capacity);
        events.add(event);
        System.out.println("[EventController] Created: " + event);
        return event;
    }

    // ----------------------------------------------------------------
    // 2.  deleteEvent
    // ----------------------------------------------------------------
    /**
     * Removes an event by its ID.
     *
     * @param eventId ID of the event to remove
     * @return true if removed; false if no event with that ID was found
     */
    public boolean deleteEvent(String eventId) {
        Optional<Event> target = findById(eventId);
        if (target.isPresent()) {
            events.remove(target.get());
            System.out.println("[EventController] Deleted event: " + eventId);
            return true;
        }
        System.out.println("[EventController] deleteEvent – event not found: " + eventId);
        return false;
    }

    // ----------------------------------------------------------------
    // 3.  searchEvent
    // ----------------------------------------------------------------
    /**
     * Case-insensitive search across event name, date, and location.
     *
     * @param keyword The search term (can be partial)
     * @return A list of matching events (may be empty, never null)
     */
    public List<Event> searchEvent(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return new ArrayList<>(events); // return all if no keyword
        }
        String kw = keyword.trim().toLowerCase();
        return events.stream()
                .filter(e -> e.getName().toLowerCase().contains(kw)
                          || e.getDate().toLowerCase().contains(kw)
                          || e.getLocation().toLowerCase().contains(kw)
                          || e.getEventId().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------
    // 4.  listEvents
    // ----------------------------------------------------------------
    /**
     * Returns a snapshot of all events currently in the system.
     *
     * @return new ArrayList containing all events
     */
    public List<Event> listEvents() {
        return new ArrayList<>(events);
    }

    // ----------------------------------------------------------------
    // 5.  Capacity check  –  used by Registration logic (Person 2 / 3)
    // ----------------------------------------------------------------
    /**
     * Checks whether the event has capacity and, if so, increments the
     * registered-count to "reserve" the spot.
     *
     * Called by the Registration sub-system before creating a Registration.
     *
     * @param eventId ID of the target event
     * @throws EventFullException      if the event is already at capacity
     * @throws IllegalArgumentException if no event with that ID exists
     */
    public void reserveSpot(String eventId) throws EventFullException {
        Event event = findById(eventId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Event not found: " + eventId));

        if (event.isFull()) {
            throw new EventFullException(eventId,
                    "Cannot register – event '" + event.getName()
                    + "' (" + eventId + ") is full. Capacity: " + event.getCapacity());
        }
        event.incrementRegistered();
        System.out.println("[EventController] Spot reserved in '" + event.getName()
                + "'. Remaining spots: " + event.availableSpots());
    }

    /**
     * Releases a previously reserved spot (called on registration cancellation).
     *
     * @param eventId ID of the target event
     * @throws IllegalArgumentException if no event with that ID exists
     */
    public void releaseSpot(String eventId) {
        Event event = findById(eventId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Event not found: " + eventId));
        event.decrementRegistered();
        System.out.println("[EventController] Spot released in '" + event.getName()
                + "'. Remaining spots: " + event.availableSpots());
    }

    // ----------------------------------------------------------------
    // 6.  Event ↔️ Registration relationship helper
    // ----------------------------------------------------------------
    /**
     * Returns the Event object for a given ID.
     * Intended as the bridge between EventController and RegistrationController.
     *
     * @param eventId ID to look up
     * @return Optional<Event> – present if found, empty otherwise
     */
    public Optional<Event> getEventById(String eventId) {
        return findById(eventId);
    }

    // ----------------------------------------------------------------
    // Private helpers
    // ----------------------------------------------------------------
    private Optional<Event> findById(String eventId) {
        return events.stream()
                .filter(e -> e.getEventId().equals(eventId))
                .findFirst();
    }
}
