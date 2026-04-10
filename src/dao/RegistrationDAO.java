package dao;

import java.sql.*;

import advance.oop.lab.Participants;
import model.*;

public class RegistrationDAO {

	public void register(int eventId, int participantId) {

	    try {
	        Connection con = DBConnection.getConnection();

	        String query = "INSERT INTO registration (event_id, participant_id, registration_date) VALUES (?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setInt(1, eventId);
	        ps.setInt(2, participantId);
	        ps.setDate(3, Date.valueOf(java.time.LocalDate.now()));

	        ps.executeUpdate();

	        System.out.println("Registered!");

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
}
