package Controller;

import Model.Event;
import Model.Participant;
import Model.Registration;
import Exception.EventFullException;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationController {

    public boolean registerToDB(int eventId, int participantId) throws EventFullException {

        String insertSql = "INSERT INTO registration (event_id, participant_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            // 🔹 Check Event
            Event event = getEventById(conn, eventId);
            if (event == null) {
                throw new Exception("Event not found!");
            }

            // 🔹 Check Participant
            Participant participant = getParticipantById(conn, participantId);
            if (participant == null) {
                throw new Exception("Participant not found!");
            }

            // 🔹 Check duplicate
            if (isAlreadyRegistered(conn, eventId, participantId)) {
                throw new Exception("Already registered!");
            }

            // 🔥 REQUIRED (Custom Exception)
            if (getRegistrationCount(conn, eventId) >= event.getMaxParticipants()) {
                throw new EventFullException("Event is full!");
            }

            // 🔹 Insert
            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setInt(1, eventId);
                stmt.setInt(2, participantId);
                return stmt.executeUpdate() > 0;
            }

        } catch (EventFullException e) {
            throw e; // rethrow
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= GET ALL REGISTRATIONS =================
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

                Participant p = new Participant(
                        rs.getInt("participant_id"),
                        rs.getString("participant_name"),
                        rs.getString("email")
                );

                list.add(new Registration(event, p));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= HELPERS =================

    private Event getEventById(Connection conn, int id) throws Exception {

        String sql = "SELECT * FROM event WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("date"),
                        rs.getInt("max_participants")
                );
            }
        }

        return null;
    }

    private Participant getParticipantById(Connection conn, int id) throws Exception {

        String sql = "SELECT * FROM participant WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Participant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        }

        return null;
    }

    private int getRegistrationCount(Connection conn, int eventId) throws Exception {

        String sql = "SELECT COUNT(*) FROM registration WHERE event_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);
        }

        return 0;
    }

    private boolean isAlreadyRegistered(Connection conn, int eventId, int participantId) throws Exception {

        String sql = "SELECT COUNT(*) FROM registration WHERE event_id=? AND participant_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ps.setInt(2, participantId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1) > 0;
        }

        return false;
    }
}