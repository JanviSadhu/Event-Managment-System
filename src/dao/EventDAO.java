package dao;

import java.sql.*;
import model.Event;

public class EventDAO {

	public int addEvent(Event e) {

	    int generatedId = -1;

	    try {
	        Connection con = DBConnection.getConnection();

	        String query = "INSERT INTO event (name, maxParticipants) VALUES (?, ?)";
	        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

	        ps.setString(1, e.getName());
	        ps.setInt(2, e.getMaxParticipants());

	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            generatedId = rs.getInt(1);
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    return generatedId;
	}
}