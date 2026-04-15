package Controller;

import DAO.EventDAO;
import Model.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EventForm extends JFrame {

    private JTextField txtId = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtLocation = new JTextField();
    private JTextField txtDate = new JTextField();
    private JTextField txtMax = new JTextField();

    private JButton btnAdd = new JButton("Add Event");
    private JButton btnDelete = new JButton("Delete Event");
    private JButton btnRefresh = new JButton("Refresh");

    private JTable table = new JTable();
    private DefaultTableModel model;

    private EventDAO dao = new EventDAO();

    public EventForm() {

        setTitle("Event Management System");
        setSize(900, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ================= LABELS =================
        JLabel l1 = new JLabel("ID:");
        JLabel l2 = new JLabel("Name:");
        JLabel l3 = new JLabel("Location:");
        JLabel l4 = new JLabel("Date:");
        JLabel l5 = new JLabel("Max Participants:");

        // ================= POSITIONING =================
        l1.setBounds(20, 20, 120, 25);
        txtId.setBounds(150, 20, 150, 25);

        l2.setBounds(20, 60, 120, 25);
        txtName.setBounds(150, 60, 150, 25);

        l3.setBounds(20, 100, 120, 25);
        txtLocation.setBounds(150, 100, 150, 25);

        l4.setBounds(20, 140, 120, 25);
        txtDate.setBounds(150, 140, 150, 25);

        l5.setBounds(20, 180, 150, 25);
        txtMax.setBounds(150, 180, 150, 25);

        btnAdd.setBounds(20, 230, 120, 30);
        btnDelete.setBounds(150, 230, 120, 30);
        btnRefresh.setBounds(280, 230, 120, 30);

        // ================= TABLE =================
        model = new DefaultTableModel();
        table.setModel(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Location");
        model.addColumn("Date");
        model.addColumn("Max Participants");

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(420, 20, 450, 400);

        // ================= ADD COMPONENTS =================
        add(l1); add(txtId);
        add(l2); add(txtName);
        add(l3); add(txtLocation);
        add(l4); add(txtDate);
        add(l5); add(txtMax);

        add(btnAdd);
        add(btnDelete);
        add(btnRefresh);
        add(sp);

        // ================= LOAD TABLE =================
        loadTable();

        // ================= ACTIONS =================
        btnAdd.addActionListener(e -> addEvent());
        btnDelete.addActionListener(e -> deleteEvent());
        btnRefresh.addActionListener(e -> loadTable());

        setVisible(true);
    }

    // ================= ADD EVENT (WITH ID) =================
    private void addEvent() {

        try {
            Event event = new Event(
                    Integer.parseInt(txtId.getText()),   // ✅ ID NOW USED
                    txtName.getText(),
                    txtLocation.getText(),
                    txtDate.getText(),
                    Integer.parseInt(txtMax.getText())
            );

            dao.addEvent(event);

            JOptionPane.showMessageDialog(this, "Event Added Successfully!");

            clearFields();
            loadTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // ================= DELETE EVENT =================
    private void deleteEvent() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first!");
            return;
        }

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        dao.deleteEvent(id);

        JOptionPane.showMessageDialog(this, "Event Deleted!");

        loadTable();
    }

    // ================= LOAD TABLE =================
    private void loadTable() {

        model.setRowCount(0);

        List<Event> list = dao.getAllEvents();

        for (Event e : list) {

            model.addRow(new Object[]{
                    e.getId(),
                    e.getName(),
                    e.getLocation(),
                    e.getDate(),
                    e.getMaxParticipants()
            });
        }
    }

    // ================= CLEAR FIELDS =================
    private void clearFields() {

        txtId.setText("");
        txtName.setText("");
        txtLocation.setText("");
        txtDate.setText("");
        txtMax.setText("");
    }
}