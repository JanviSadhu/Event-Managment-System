package controller;

import model.Participant;
import java.util.ArrayList;

public class ParticipantController {

    private ArrayList<Participant> participants;

    public ParticipantController() {
        participants = new ArrayList<>();
    }

    // Add new participant
    public void addParticipant(Participant p) {
        participants.add(p);
        System.out.println("Participant added successfully!");
    }

    // Get all participants
    public ArrayList<Participant> getAllParticipants() {
        return participants;
    }

    // Search participant by ID
    public Participant findParticipantById(int id) {
        for (Participant p : participants) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Remove participant
    public void removeParticipant(int id) {
        Participant p = findParticipantById(id);
        if (p != null) {
            participants.remove(p);
            System.out.println("Participant removed.");
        } else {
            System.out.println("Participant not found.");
        }
    }
}
