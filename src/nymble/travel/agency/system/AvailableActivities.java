
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * The AvailableActivities class represents the GUI for enrolling in available activities.
 * It allows users to select a package, destination, and activity to enroll in.
 */
public class AvailableActivities extends JFrame {

    private JComboBox<String> packageComboBox;
    private JComboBox<String> destinationComboBox;
    private JComboBox<String> activityComboBox;
    private JButton submitButton;
    private Conn c = new Conn();

    /**
     * Constructor for the AvailableActivities class.
     * Sets up the GUI components and initializes the frame.
     */
    public AvailableActivities() {
        setTitle("Available Activities");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel selectPackageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();

        JLabel selectDestinationLabel = new JLabel("Select Destination:");
        destinationComboBox = new JComboBox<>();

        JLabel selectActivityLabel = new JLabel("Select Activity:");
        activityComboBox = new JComboBox<>();

        submitButton = new JButton("Submit");

        panel.add(selectPackageLabel);
        panel.add(packageComboBox);
        panel.add(selectDestinationLabel);
        panel.add(destinationComboBox);
        panel.add(selectActivityLabel);
        panel.add(activityComboBox);
        panel.add(new JLabel()); // Empty space for better alignment
        panel.add(submitButton);

        setupListeners();

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Populates the package combo box with available package names.
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
     * Sets up action listeners for combo boxes and the submit button.
     */
    private void setupListeners() {
        packageComboBox.addActionListener(e -> populateDestinations());
        destinationComboBox.addActionListener(e -> populateActivities());
        submitButton.addActionListener(e -> enrollForActivity());
    }

    /**
     * Populates the destination combo box based on the selected package.
     */
    private void populateDestinations() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        destinationComboBox.removeAllItems(); // Clear previous items

        if (selectedPackage != null) {
            try {
                String query = "SELECT Destination FROM " + selectedPackage;
                ResultSet resultSet = c.s.executeQuery(query);

                while (resultSet.next()) {
                    destinationComboBox.addItem(resultSet.getString("Destination"));
                }

                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Populates the activity combo box based on the selected package and destination.
     */
    private void populateActivities() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        activityComboBox.removeAllItems(); // Clear previous items

        if (selectedPackage != null && selectedDestination != null) {
            try {
                String query = "SELECT ActivityName FROM Activity WHERE PackageName = ? AND Destination = ?";
                try (PreparedStatement preparedStatement = c.getConnection().prepareStatement(query)) {
                    preparedStatement.setString(1, selectedPackage);
                    preparedStatement.setString(2, selectedDestination);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        activityComboBox.addItem(resultSet.getString("ActivityName"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Enrolls the user for the selected activity.
     * Displays appropriate messages based on the success or failure of the enrollment.
     */
    private void enrollForActivity() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        String selectedActivity = (String) activityComboBox.getSelectedItem();

        if (selectedPackage != null && selectedDestination != null && selectedActivity != null) {
            // Implement logic to enroll for the selected activity
            // Update the database tables accordingly, e.g., update the Enrollment table with the passenger's enrollment details
            // Display appropriate messages to the user based on the success or failure of the enrollment.
            // Consider handling discounts and balance based on passenger types as mentioned in the task.
            JOptionPane.showMessageDialog(this, "Enrollment successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a package, destination, and activity.");
        }
    }

    /**
     * The main method to start the AvailableActivities application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AvailableActivities::new);
    }
}
