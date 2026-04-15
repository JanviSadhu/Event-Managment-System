package Controller;

import DAO.ParticipantDAO;
import Model.Participant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ParticipantForm extends JFrame {

    private JTextField txtId = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtEmail = new JTextField();


    private JButton btnAdd = new JButton("Add Participant");
    private JButton btnDelete = new JButton("Delete Participant");
    private JButton btnRefresh = new JButton("Refresh");

    private JTable table = new JTable();
    private DefaultTableModel model;

    private ParticipantDAO dao = new ParticipantDAO();

    public ParticipantForm() {

        setTitle("Participant Management");
        setSize(850, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // LABELS
        JLabel l1 = new JLabel("ID:");
        JLabel l2 = new JLabel("Name:");
        JLabel l3 = new JLabel("Email:");


        l1.setBounds(20, 20, 100, 25);
        txtId.setBounds(150, 20, 150, 25);

        l2.setBounds(20, 60, 100, 25);
        txtName.setBounds(150, 60, 150, 25);

        l3.setBounds(20, 100, 100, 25);
        txtEmail.setBounds(150, 100, 150, 25);


        btnAdd.setBounds(20, 190, 150, 30);
        btnDelete.setBounds(180, 190, 150, 30);
        btnRefresh.setBounds(340, 190, 150, 30);

        // TABLE
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Email");


        table.setModel(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(420, 20, 400, 400);

        // ADD COMPONENTS
        add(l1); add(txtId);
        add(l2); add(txtName);
        add(l3); add(txtEmail);


        add(btnAdd);
        add(btnDelete);
        add(btnRefresh);
        add(sp);

        loadTable();

        // ACTIONS
        btnAdd.addActionListener(e -> addParticipant());
        btnDelete.addActionListener(e -> deleteParticipant());
        btnRefresh.addActionListener(e -> loadTable());

        setVisible(true);
    }

    // ADD
    private void addParticipant() {

        try {
            Participant p = new Participant(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtEmail.getText()
            );

            dao.addParticipant(p);

            JOptionPane.showMessageDialog(this, "Participant Added!");

            clearFields();
            loadTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // DELETE
    private void deleteParticipant() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row!");
            return;
        }

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        dao.deleteParticipant(id);

        JOptionPane.showMessageDialog(this, "Deleted!");

        loadTable();
    }

    // LOAD TABLE
    private void loadTable() {

        model.setRowCount(0);

        List<Participant> list = dao.getAllParticipants();

        for (Participant p : list) {

            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getEmail()
            });
        }
    }

    private void clearFields() {

        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
    }
}