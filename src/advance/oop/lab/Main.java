package advance.oop.lab;

import dao.*;
import model.*;

public class Main {

    public static void main(String[] args) {

        EventDAO eventDAO = new EventDAO();
        ParticipantDAO participantDAO = new ParticipantDAO();
        RegistrationDAO registrationDAO = new RegistrationDAO();

        Event e = new Event("Food Fest", 50);
        Participant p = new Participant("Sam", "sam@gmail.com");

        int eventId = eventDAO.addEvent(e);
        int participantId = participantDAO.addParticipant(p);

        registrationDAO.register(eventId, participantId);
    }
}