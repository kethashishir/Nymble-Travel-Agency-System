
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The CustomerActivityEnrollment class represents the GUI for customer activity enrollment.
 */
public class CustomerActivityEnrollment extends JFrame {

    private JComboBox<String> packageComboBox;
    private JComboBox<String> destinationComboBox;
    private JComboBox<String> activityComboBox;
    private JTextField passengerNameField;
    private JButton enrollButton;
    private Conn c = new Conn();

    /**
     * Constructs an instance of CustomerActivityEnrollment.
     */
    public CustomerActivityEnrollment() {
        setTitle("Activity Enrollment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel selectPackageLabel = new JLabel("Select Travel Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();

        JLabel selectDestinationLabel = new JLabel("Select Destination:");
        destinationComboBox = new JComboBox<>();

        JLabel selectActivityLabel = new JLabel("Select Activity:");
        activityComboBox = new JComboBox<>();

        JLabel passengerNameLabel = new JLabel("Enter Passenger Name:");
        passengerNameField = new JTextField();

        enrollButton = new JButton("Enroll for Activity");

        panel.add(selectPackageLabel);
        panel.add(packageComboBox);
        panel.add(selectDestinationLabel);
        panel.add(destinationComboBox);
        panel.add(selectActivityLabel);
        panel.add(activityComboBox);
        panel.add(passengerNameLabel);
        panel.add(passengerNameField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(enrollButton);

        setupListeners();

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Populates the travel package combo box with data from the database.
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
     * Populates the destination combo box based on the selected travel package.
     *
     * @param packageName The selected travel package.
     */
    private void populateDestinationComboBox(String packageName) {
        destinationComboBox.removeAllItems();

        try {
            String query = "SELECT Destination FROM Itinerary WHERE Package_name = '" + packageName + "'";
            ResultSet resultSet = c.s.executeQuery(query);

            while (resultSet.next()) {
                destinationComboBox.addItem(resultSet.getString("Destination"));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Populates the activity combo box based on the selected travel package and destination.
     *
     * @param packageName    The selected travel package.
     * @param selectedDestination The selected destination.
     */
    private void populateActivityComboBox(String packageName, String selectedDestination) {
        activityComboBox.removeAllItems();

        try {
            String query = "SELECT Activity_name FROM " + packageName +
                    " WHERE Destination = '" + selectedDestination + "'";
            ResultSet resultSet = c.s.executeQuery(query);

            while (resultSet.next()) {
                activityComboBox.addItem(resultSet.getString("Activity_name"));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up action listeners for combo boxes and buttons.
     */
    private void setupListeners() {
        packageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPackage = (String) packageComboBox.getSelectedItem();
                if (selectedPackage != null) {
                    populateDestinationComboBox(selectedPackage);
                }
            }
        });

        destinationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPackage = (String) packageComboBox.getSelectedItem();
                String selectedDestination = (String) destinationComboBox.getSelectedItem();

                if (selectedPackage != null && selectedDestination != null) {
                    populateActivityComboBox(selectedPackage, selectedDestination);
                }
            }
        });

        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollForActivity();
            }
        });
    }

    /**
     * Handles the enrollment process for the selected activity.
     */
    private void enrollForActivity() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        String selectedActivity = (String) activityComboBox.getSelectedItem();
        String passengerName = passengerNameField.getText();

        if (selectedPackage != null && selectedDestination != null && selectedActivity != null
                && !passengerName.isEmpty()) {
            // Implement logic for enrollment, discount, and balance handling
            // Update the database accordingly
            // Display appropriate messages to the user
            JOptionPane.showMessageDialog(this, "Enrollment successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomerActivityEnrollment::new);
    }
}
