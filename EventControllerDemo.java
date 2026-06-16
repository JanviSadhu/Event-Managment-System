/**
 * Quick smoke-test for EventController.
 * Run:  javac *.java && java EventControllerDemo
 */
public class EventControllerDemo {

    public static void main(String[] args) {

        EventController controller = new EventController();

        // --- createEvent ---
        System.out.println("=== createEvent ===");
        controller.createEvent("EVT-001", "Java Conference", "2025-09-15", "Hall A", 3);
        controller.createEvent("EVT-002", "Spring Workshop",  "2025-10-01", "Room 12", 2);
        controller.createEvent("EVT-003", "Design Bootcamp", "2025-11-20", "Hall B",  50);

        // --- listEvents ---
        System.out.println("\n=== listEvents ===");
        controller.listEvents().forEach(System.out::println);

        // --- searchEvent ---
        System.out.println("\n=== searchEvent('hall') ===");
        controller.searchEvent("hall").forEach(System.out::println);

        System.out.println("\n=== searchEvent('2025-10') ===");
        controller.searchEvent("2025-10").forEach(System.out::println);

        // --- reserveSpot / capacity check ---
        System.out.println("\n=== reserveSpot (capacity = 2 for EVT-002) ===");
        try {
            controller.reserveSpot("EVT-002");  // 1st spot
            controller.reserveSpot("EVT-002");  // 2nd spot  (now full)
            controller.reserveSpot("EVT-002");  // should throw EventFullException
        } catch (EventFullException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        // --- releaseSpot ---
        System.out.println("\n=== releaseSpot ===");
        controller.releaseSpot("EVT-002");

        // --- deleteEvent ---
        System.out.println("\n=== deleteEvent ===");
        controller.deleteEvent("EVT-001");
        System.out.println("Events after deletion:");
        controller.listEvents().forEach(System.out::println);

        // --- getEventById ---
        System.out.println("\n=== getEventById('EVT-002') ===");
        controller.getEventById("EVT-002")
                  .ifPresent(e -> System.out.println("Found: " + e));
    }
}
