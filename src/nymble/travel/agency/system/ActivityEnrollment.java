
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * This class represents the Activity Enrollment window for the travel agency system.
 * Customers can select a travel package, destination, and enroll for activities.
 */
public class ActivityEnrollment extends JFrame {

    // GUI components
    public JComboBox<String> packageComboBox;
    public JComboBox<String> destinationComboBox;
    public JList<String> activityList;
    public JComboBox<String> passengerTypeComboBox;
    public JButton enrollButton;
    public Conn c = new Conn();

    /**
     * Constructor for the ActivityEnrollment class.
     * Sets up the GUI components and initializes the frame.
     */
    public ActivityEnrollment() {
        setTitle("Activity Enrollment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Labels and input components
        JLabel selectPackageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();

        JLabel selectDestinationLabel = new JLabel("Select Destination:");
        destinationComboBox = new JComboBox<>();

        JLabel activityListLabel = new JLabel("Available Activities:");
        activityList = new JList<>();
        JScrollPane listScrollPane = new JScrollPane(activityList);

        JLabel passengerTypeLabel = new JLabel("Select Passenger Type:");
        passengerTypeComboBox = new JComboBox<>(new String[]{"Standard", "Gold", "Premium"});

        enrollButton = new JButton("Enroll");

        // Adding components to the panel
        panel.add(selectPackageLabel);
        panel.add(packageComboBox);
        panel.add(selectDestinationLabel);
        panel.add(destinationComboBox);
        panel.add(activityListLabel);
        panel.add(listScrollPane);
        panel.add(passengerTypeLabel);
        panel.add(passengerTypeComboBox);
        panel.add(enrollButton);
        panel.add(new JLabel()); // Empty label for better alignment

        // Setting up event listeners
        setupListeners();

        // Adding the panel to the frame
        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Populates the package combo box with available travel packages from the database.
     */
    public void populatePackageComboBox() {
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
     * Sets up event listeners for combo boxes and the enroll button.
     */
    public void setupListeners() {
        packageComboBox.addActionListener(e -> populateDestinationComboBox());
        destinationComboBox.addActionListener(e -> populateActivityList());
        enrollButton.addActionListener(e -> enrollPassenger());
    }

    /**
     * Populates the destination combo box based on the selected travel package.
     */
    public void populateDestinationComboBox() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        destinationComboBox.removeAllItems();

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
     * Populates the activity list based on the selected destination.
     */
    public void populateActivityList() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        DefaultListModel<String> model = new DefaultListModel<>();

        if (selectedPackage != null && selectedDestination != null) {
            try {
                String query = "SELECT ActivityName FROM " + selectedDestination;
                ResultSet resultSet = c.s.executeQuery(query);

                while (resultSet.next()) {
                    model.addElement(resultSet.getString("ActivityName"));
                }

                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        activityList.setModel(model);
    }

    /**
     * Enrolls the passenger for the selected activity.
     */
    public void enrollPassenger() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        String selectedActivity = activityList.getSelectedValue();
        String selectedPassengerType = (String) passengerTypeComboBox.getSelectedItem();

        if (selectedPackage != null && selectedDestination != null && selectedActivity != null && selectedPassengerType != null) {
            try {
                // Check if the selected activity exists
                String activityQuery = "SELECT * FROM " + selectedDestination + " WHERE ActivityName=?";
                PreparedStatement activityStatement = c.getConnection().prepareStatement(activityQuery);
                activityStatement.setString(1, selectedActivity);
                ResultSet activityResultSet = activityStatement.executeQuery();

                if (activityResultSet.next()) {
                    double activityCost = activityResultSet.getDouble("Cost");
                    int activityCapacity = activityResultSet.getInt("Capacity");

                    // Calculate total price based on passenger type
                    double totalPrice = calculatePrice(selectedPassengerType, activityCost);

                    // Check if there is available capacity for the activity
                    if (activityCapacity > 0) {
                        // Insert enrollment record into the database
                        String enrollQuery = "INSERT INTO Enrollments (Package_name, Destination, ActivityName, PassengerType, Price) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement enrollStatement = c.getConnection().prepareStatement(enrollQuery);
                        enrollStatement.setString(1, selectedPackage);
                        enrollStatement.setString(2, selectedDestination);
                        enrollStatement.setString(3, selectedActivity);
                        enrollStatement.setString(4, selectedPassengerType);
                        enrollStatement.setDouble(5, totalPrice);
                        enrollStatement.executeUpdate();

                        // Update activity capacity
                        String updateCapacityQuery = "UPDATE " + selectedDestination + " SET Capacity=? WHERE ActivityName=?";
                        PreparedStatement updateCapacityStatement = c.getConnection().prepareStatement(updateCapacityQuery);
                        updateCapacityStatement.setInt(1, activityCapacity - 1);
                        updateCapacityStatement.setString(2, selectedActivity);
                        updateCapacityStatement.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Passenger enrolled successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Activity is full. Cannot enroll more passengers.");
                    }
                }

                // Close result set and statements
                activityResultSet.close();
                activityStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error enrolling passenger. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a package, destination, activity, and passenger type.");
        }
    }

    /**
     * Calculates the price of the activity based on the passenger type.
     *
     * @param passengerType The selected passenger type.
     * @param activityCost  The original cost of the activity.
     * @return The calculated total price.
     */
    public double calculatePrice(String passengerType, double activityCost) {
        switch (passengerType) {
            case "Standard":
                return activityCost;
            case "Gold":
                return 0.9 * activityCost; // 10% discount for Gold passengers
            case "Premium":
                return 0; // Premium passengers get the activity for free
            default:
                return 0;
        }
    }

    /**
     * The main method to start the ActivityEnrollment application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ActivityEnrollment::new);
    }
}
