package DAO;

import Model.Event;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public void addEvent(Event event) {

        String sql = "INSERT INTO event(id, name, location, date, max_participants) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, event.getId());
            ps.setString(2, event.getName());
            ps.setString(3, event.getLocation());
            ps.setString(4, event.getDate());
            ps.setInt(5, event.getMaxParticipants());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error while adding event: " + e.getMessage());
        }
    }

    public void deleteEvent(int id) {

        String sql = "DELETE FROM event WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error while deleting event: " + e.getMessage());
        }
    }

    public List<Event> getAllEvents() {

        List<Event> list = new ArrayList<>();

        String sql = "SELECT * FROM event";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Event e = new Event();

                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setLocation(rs.getString("location"));
                e.setDate(rs.getString("date"));
                e.setMaxParticipants(rs.getInt("max_participants"));

                list.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching events: " + e.getMessage());
        }

        return list;
    }

    public Event getEventById(int id) {

        String sql = "SELECT * FROM event WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public void updateEvent(Event event) {

        String sql = "UPDATE event SET name=?, location=?, date=?, max_participants=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, event.getName());
            ps.setString(2, event.getLocation());
            ps.setString(3, event.getDate());
            ps.setInt(4, event.getMaxParticipants());
            ps.setInt(5, event.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error while updating event: " + e.getMessage());
        }
    }
}