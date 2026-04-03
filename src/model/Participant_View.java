package model;

import java.util.ArrayList;
import model.Participant_View;

public class Participant_View {

	public static void displayParticipants(ArrayList<Participant_View> participants) {

        if (participants == null || participants.isEmpty()) {
            System.out.println("No participants found.");
            return;
        }

        System.out.println("\n++++++ Participants +++++++");

        for (Participant_View p : participants) {
            System.out.println("- " + p.getName());
        }
    }
}

