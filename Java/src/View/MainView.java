package View;

import Controller.EventController;
import Controller.ParticipantController;
import Controller.RegistrationController;
import Model.Event;
import Model.Participant;
import Util.FileExportUtil;
import Exception.EventFullException;
import java.util.List;
import java.util.Scanner;

public class MainView {

    private EventController eventController = new EventController();
    private ParticipantController participantController = new ParticipantController();
    private RegistrationController registrationController = new RegistrationController();

    private Scanner scanner = new Scanner(System.in);

    public void start() throws EventFullException {

        while (true) {

            System.out.println("\n========== EVENT MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Event");
            System.out.println("2. Add Participant");
            System.out.println("3. Register Participant");
            System.out.println("4. View Events");
            System.out.println("5. Export Participants (CSV)");
            System.out.println("6. Export Participants (JSON)");
            System.out.println("7. Search Event");
            System.out.println("8. Search Participant");
            System.out.println("9. Delete Event");
            System.out.println("10. Delete Participant");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addEvent();
                case 2 -> addParticipant();
                case 3 -> register();
                case 4 -> showEvents();
                case 5 -> exportParticipants();
                case 6 -> exportParticipantsJSON();
                case 7 -> searchEvent();
                case 8 -> searchParticipant();
                case 9 -> deleteEvent();
                case 10 -> deleteParticipant();
                case 11 -> {
                    System.out.println("Exiting system...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ================= EXPORT =================

    private void exportParticipants() {
        System.out.print("Enter Event ID: ");
        int eventId = scanner.nextInt();

        Event event = eventController.findEventByIdFromDB(eventId);

        if (event != null) {
            List<Participant> participants = eventController.getParticipantsForEventFromDB(eventId);
            FileExportUtil.exportParticipantsToCSV(eventId, participants);
        } else {
            System.out.println("Event not found!");
        }
    }

    private void exportParticipantsJSON() {
        System.out.print("Enter Event ID: ");
        int eventId = scanner.nextInt();

        Event event = eventController.findEventByIdFromDB(eventId);

        if (event != null) {
            List<Participant> participants = eventController.getParticipantsForEventFromDB(eventId);
            FileExportUtil.exportParticipantsToJSON(eventId, participants);
        } else {
            System.out.println("Event not found!");
        }
    }

    // ================= SEARCH =================

    private void searchEvent() {
        scanner.nextLine();
        System.out.print("Enter event name: ");
        String name = scanner.nextLine();

        Event event = eventController.searchEventInDB(name);

        if (event != null) {
            System.out.println(event);
        } else {
            System.out.println("Event not found!");
        }
    }

    private void searchParticipant() {
        scanner.nextLine();
        System.out.print("Enter participant name: ");
        String name = scanner.nextLine();

        Participant p = participantController.searchParticipantInDB(name);

        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Participant not found!");
        }
    }

    // ================= DELETE =================

    private void deleteEvent() {
        System.out.print("Enter Event ID: ");
        int id = scanner.nextInt();

        boolean deletedFromDb = eventController.deleteEventFromDB(id);
        System.out.println(deletedFromDb ? "Event deleted!" : "Event not found!");
    }

    private void deleteParticipant() {
        System.out.print("Enter Participant ID: ");
        int id = scanner.nextInt();

        boolean deletedFromDb = participantController.deleteParticipantFromDB(id);
        System.out.println(deletedFromDb ? "Participant deleted!" : "Participant not found!");
    }

    // ================= ADD EVENT =================

    private void addEvent() {

        System.out.print("Event ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Date: ");
        String date = scanner.nextLine();

        System.out.print("Max Participants: ");
        int max = scanner.nextInt();

        Event event = new Event(id, name, location, date, max);

        boolean success = eventController.saveEventToDB(event);

        if (success) {
            System.out.println("Event added successfully!");
        } else {
            System.out.println("DB save failed!");
        }
    }

    // ================= ADD PARTICIPANT =================

    private void addParticipant() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Participant participant = new Participant(id, name, email);

        boolean success = participantController.saveParticipantToDB(participant);

        if (success) {
            System.out.println("Participant added successfully!");
        } else {
            System.out.println("DB save failed!");
        }
    }

    // ================= REGISTER =================

    private void register() throws EventFullException {
        System.out.print("Event ID: ");
        int eventId = scanner.nextInt();

        System.out.print("Participant ID: ");
        int participantId = scanner.nextInt();

        boolean success = registrationController.registerToDB(eventId, participantId);

        if (success) {
            System.out.println("Registered successfully!");
        } else {
            System.out.println("Registration failed!");
        }
    }

    // ================= VIEW =================

    private void showEvents() {

        System.out.println(" EVENTS FROM DATABASE:");

        eventController.getEventsFromDB().forEach(System.out::println);
    }
}
