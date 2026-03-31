package controller;

import model.Event;
import model.Participant;
import model.Registration;
import exception.EventFullException;

import java.util.ArrayList;

public class RegistrationController {

    private ArrayList<Registration> registrations;

    public RegistrationController() {
        registrations = new ArrayList<>();
    }

    // Register participant to event
    public void registerParticipantToEvent(Event event, Participant participant) throws EventFullException {

        if (event.isFull()) {
            throw new EventFullException("Event is full! Cannot register.");
        }

        // Add participant to event
        event.addParticipant();

        // Create registration
        Registration reg = new Registration(registrations.size() + 1, event, participant);
        registrations.add(reg);

        System.out.println("Participant registered successfully!");
    }

    // Get participants of a specific event
    public ArrayList<Participant> viewParticipantsByEvent(Event event) {
        ArrayList<Participant> eventParticipants = new ArrayList<>();
        
        for (Registration reg : registrations) {
            if (reg.getEvent().getEventId() == event.getEventId()) {
                eventParticipants.add(reg.getParticipant());
            }
        }
        
        return eventParticipants;
    }

    // Get all registrations
    public ArrayList<Registration> getAllRegistrations() {
        return registrations;
    }
}
