
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * The PassengerManagement class represents a window for managing passengers.
 */
public class PassengerManagement extends JFrame {

    private JComboBox<String> packageComboBox;
    private JList<String> passengerList;
    private JButton viewDetailsButton;
    private JButton editButton;
    private JButton deleteButton;
    private Conn c = new Conn();

    /**
     * Constructs an instance of PassengerManagement.
     */
    public PassengerManagement() {
        setTitle("Passenger Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel selectPackageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();

        JLabel passengerListLabel = new JLabel("Passenger List:");
        passengerList = new JList<>();
        JScrollPane listScrollPane = new JScrollPane(passengerList);

        viewDetailsButton = new JButton("View Details");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        panel.add(selectPackageLabel);
        panel.add(packageComboBox);
        panel.add(passengerListLabel);
        panel.add(listScrollPane);
        panel.add(viewDetailsButton);
        panel.add(new JLabel()); // Empty space for better alignment
        panel.add(editButton);
        panel.add(deleteButton);

        setupListeners();

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Populates the travel package combo box with available packages.
     */
    private void populatePackageComboBox() {
        try {
            String query = "SELECT Package_name FROM Package";
            ResultSet resultSet = c.s.executeQuery(query);

            while (resultSet.next()) {
                packageComboBox.addItem(resultSet.getString("Package_name"));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up action listeners for buttons.
     */
    private void setupListeners() {
        packageComboBox.addActionListener(e -> populatePassengerList());
        viewDetailsButton.addActionListener(e -> viewPassengerDetails());
        editButton.addActionListener(e -> editPassenger());
        deleteButton.addActionListener(e -> deletePassenger());
    }

    /**
     * Populates the passenger list based on the selected travel package.
     */
    private void populatePassengerList() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        DefaultListModel<String> model = new DefaultListModel<>();

        if (selectedPackage != null) {
            try {
                String query = "SELECT PassengerName FROM Passenger WHERE PackageName = ?";
                try (PreparedStatement preparedStatement = c.getConnection().prepareStatement(query)) {
                    preparedStatement.setString(1, selectedPackage);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        model.addElement(resultSet.getString("PassengerName"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        passengerList.setModel(model);
    }

    /**
     * Displays details of the selected passenger.
     */
    private void viewPassengerDetails() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedPassenger = passengerList.getSelectedValue();

        if (selectedPackage != null && selectedPassenger != null) {
            // Implement logic to view passenger details
            // Fetch and display details of the selected passenger (name, passenger number, balance, activities signed up for)
            JOptionPane.showMessageDialog(this, "View Details - Package: " + selectedPackage + ", Passenger: " + selectedPassenger);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a package and a passenger.");
        }
    }

    /**
     * Allows the user to edit details of the selected passenger.
     */
    private void editPassenger() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedPassenger = passengerList.getSelectedValue();

        if (selectedPackage != null && selectedPassenger != null) {
            // Implement logic to edit passenger details
            // Allow the user to edit details of the selected passenger
            JOptionPane.showMessageDialog(this, "Edit Passenger - Package: " + selectedPackage + ", Passenger: " + selectedPassenger);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a package and a passenger.");
        }
    }

    /**
     * Deletes the selected passenger from the database.
     */
    private void deletePassenger() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedPassenger = passengerList.getSelectedValue();

        if (selectedPackage != null && selectedPassenger != null) {
            // Implement logic to delete passenger
            // Delete the selected passenger from the database
            JOptionPane.showMessageDialog(this, "Delete Passenger - Package: " + selectedPackage + ", Passenger: " + selectedPassenger);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a package and a passenger.");
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PassengerManagement::new);
    }
}


