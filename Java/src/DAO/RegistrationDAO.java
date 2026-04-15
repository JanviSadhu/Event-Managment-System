package DAO;

import Model.Event;
import Model.Participant;
import Model.Registration;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    // ================= REGISTER =================
    public boolean registerParticipant(int eventId, int participantId) {

        String sql = "INSERT INTO registration (event_id, participant_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, eventId);
            ps.setInt(2, participantId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error while registering: " + e.getMessage());
            return false;
        }
    }

    // ================= GET ALL =================
    public List<Registration> getAllRegistrations() {

        List<Registration> list = new ArrayList<>();

        String sql = """
                SELECT e.id AS event_id, e.name AS event_name,
                       p.id AS participant_id, p.name AS participant_name, p.email
                FROM registration r
                JOIN event e ON r.event_id = e.id
                JOIN participant p ON r.participant_id = p.id
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Event event = new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        "", "", 0
                );

                Participant participant = new Participant(
                        rs.getInt("participant_id"),
                        rs.getString("participant_name"),
                        rs.getString("email")
                );

                list.add(new Registration(event, participant));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching registrations: " + e.getMessage());
        }

        return list;
    }

    // ================= DELETE =================
    public void deleteRegistration(int eventId, int participantId) {

        String sql = "DELETE FROM registration WHERE event_id=? AND participant_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, eventId);
            ps.setInt(2, participantId);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error deleting registration: " + e.getMessage());
        }
    }
}