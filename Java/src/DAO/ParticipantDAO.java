package DAO;

import Model.Participant;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    public void addParticipant(Participant p) {

        String sql = "INSERT INTO participant(id, name, email) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getEmail());

            ps.executeUpdate();

            System.out.println("Participant added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteParticipant(int id) {

        String sql = "DELETE FROM participant WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error deleting participant: " + e.getMessage());
        }
    }

    // GET ALL
    public List<Participant> getAllParticipants() {

        List<Participant> list = new ArrayList<>();

        String sql = "SELECT * FROM participant";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Participant p = new Participant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching participants: " + e.getMessage());
        }

        return list;
    }
    public Participant getParticipantById(int id) {

        String sql = "SELECT * FROM participant WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Participant(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}