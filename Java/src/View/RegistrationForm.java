package View;

import Controller.RegistrationController;
import Exception.EventFullException;
import Model.Registration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RegistrationForm extends JFrame {

    private JTextField txtEventId = new JTextField();
    private JTextField txtParticipantId = new JTextField();

    private JButton btnRegister = new JButton("Register");
    private JButton btnRefresh = new JButton("Refresh");

    private JTable table = new JTable();
    private DefaultTableModel model;

    private RegistrationController controller = new RegistrationController();

    public RegistrationForm() {

        setTitle("Registration System");
        setSize(700, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Labels
        JLabel l1 = new JLabel("Event ID:");
        JLabel l2 = new JLabel("Participant ID:");

        l1.setBounds(20, 20, 120, 25);
        txtEventId.setBounds(150, 20, 150, 25);

        l2.setBounds(20, 60, 120, 25);
        txtParticipantId.setBounds(150, 60, 150, 25);

        btnRegister.setBounds(20, 100, 120, 30);
        btnRefresh.setBounds(150, 100, 120, 30);

        // Table
        model = new DefaultTableModel();
        table.setModel(model);

        model.addColumn("Event ID");
        model.addColumn("Event Name");
        model.addColumn("Participant ID");
        model.addColumn("Participant Name");

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(320, 20, 350, 300);

        // Add
        add(l1); add(txtEventId);
        add(l2); add(txtParticipantId);
        add(btnRegister); add(btnRefresh);
        add(sp);

        loadTable();

        // Actions
        btnRegister.addActionListener(e -> register());
        btnRefresh.addActionListener(e -> loadTable());

        setVisible(true);
    }

    private void register() {

        try {
            int eventId = Integer.parseInt(txtEventId.getText());
            int participantId = Integer.parseInt(txtParticipantId.getText());

            boolean success = controller.registerToDB(eventId, participantId);

            if (success) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                loadTable();
            }

        } catch (EventFullException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void loadTable() {

        model.setRowCount(0);

        List<Registration> list = controller.getAllRegistrations();

        for (Registration r : list) {
            model.addRow(new Object[]{
                    r.getEvent().getId(),
                    r.getEvent().getName(),
                    r.getParticipant().getId(),
                    r.getParticipant().getName()
            });
        }
    }
}