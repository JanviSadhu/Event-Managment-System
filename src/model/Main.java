package model;

import model.Event;
import model.Organizer;
import model.Participant;
import model.Registration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        Organizer organizer = new Organizer(1, "Tech Events Inc.", "contact@techevents.com", "123-456-7890");

      
        Event workshop = new Event(101, "Java Workshop", "Room A", LocalDate.of(2025, 5, 10), 2);
        Event summit = new Event(102, "Cloud Summit", "Hall B", LocalDate.of(2025, 6, 15), 1);

        
        organizer.addEvent(workshop);
        organizer.addEvent(summit);

        Participant alice = new Participant(1001, "Alice Johnson", "alice@email.com", "555-0101");
        Participant bob = new Participant(1002, "Bob Smith", "bob@email.com", "555-0102");
        Participant charlie = new Participant(1003, "Charlie Brown", "charlie@email.com", "555-0103");

        List<Registration> registrations = new ArrayList<>();

       
        System.out.println("=== Registrations ===\n");

      
        Registration reg1 = alice.register(workshop);
        if (reg1 != null) {
            registrations.add(reg1);
            System.out.println("Alice registered for Java Workshop");
        }

       
        Registration reg2 = bob.register(workshop);
        if (reg2 != null) {
            registrations.add(reg2);
            System.out.println("Bob registered for Java Workshop");
        }

      
        Registration reg3 = charlie.register(workshop);
        if (reg3 != null) {
            registrations.add(reg3);
        } 

       
        Registration reg4 = alice.register(summit);
        if (reg4 != null) {
            registrations.add(reg4);
            System.out.println("Alice registered for Cloud Summit");
        }

        Registration reg5 = bob.register(summit);
        if (reg5 != null) {
            registrations.add(reg5);
        }

        System.out.println("\n=== Registration Details ===");
        for (Registration r : registrations) {
            System.out.println(r);
        }

        System.out.println("\n=== Event Status ===");
        System.out.println(workshop);
        System.out.println(summit);

        System.out.println("\n=== Organizer Info ===");
        System.out.println(organizer);
        System.out.println("Events organized:");
        for (Event e : organizer.getEvents()) {
            System.out.println("  - " + e.getName() + " (Participants: " + e.getMaxParticipants() + "/" + e.getMaxParticipants() + ")");
        }

        System.out.println("\n=== Participant Info ===");
        System.out.println(alice);
        System.out.println(bob);
        System.out.println(charlie);
    }
}
