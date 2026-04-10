package dao;

import java.sql.*;
import model.Participant;

public class ParticipantDAO {

	public int addParticipant(Participant p) {

	    int generatedId = -1;

	    try {
	        Connection con = DBConnection.getConnection();

	        String query = "INSERT INTO participant (name, email) VALUES (?, ?)";
	        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

	        ps.setString(1, p.getName());
	        ps.setString(2, p.getEmail());

	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            generatedId = rs.getInt(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return generatedId;
	}
}