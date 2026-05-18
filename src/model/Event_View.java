package model;

import java.util.ArrayList;
import model.Event_View;




public class Event_View {
	
	 public static void displayEvents(ArrayList<Event_View> events) {

	        System.out.println("\n----- EVENT LIST -----");

	        if (events.isEmpty()) {
	            System.out.println("No events available.");
	            return;
	        }

	        for (Event_View e : events) {
	            System.out.println(e);
	        }
	    }

}