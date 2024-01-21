
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents the Add Activity window for the travel agency system.
 * Allows the user to add activities to destinations within selected travel packages.
 */
public class AddActivity extends JFrame {

    private JComboBox<String> packageComboBox;
    private JComboBox<String> destinationComboBox;
    private JTextField activityNameField;
    private JTextField activityDescriptionField;
    private JTextField activityCostField;
    private JTextField activityCapacityField;
    private JButton submitPackageButton;
    private JButton submitDestinationButton;
    private JButton addActivityButton, back;
    private Conn c = new Conn();

    /**
     * Constructor for the AddActivity class.
     * Sets up the GUI components and initializes the frame.
     */
    public AddActivity() {
        setTitle("Add Activity");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        // Labels and input components
        JLabel packageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();
        submitPackageButton = new JButton("Submit");

        JLabel destinationLabel = new JLabel("Select Destination:");
        destinationComboBox = new JComboBox<>();
        submitDestinationButton = new JButton("Submit");

        JLabel activityNameLabel = new JLabel("Activity Name:");
        activityNameField = new JTextField();
        activityNameField.setEnabled(false);

        JLabel activityDescriptionLabel = new JLabel("Activity Description:");
        activityDescriptionField = new JTextField();
        activityDescriptionField.setEnabled(false);

        JLabel activityCostLabel = new JLabel("Activity Cost:");
        activityCostField = new JTextField();
        activityCostField.setEnabled(false);

        JLabel activityCapacityLabel = new JLabel("Activity Capacity:");
        activityCapacityField = new JTextField();
        activityCapacityField.setEnabled(false);

        addActivityButton = new JButton("Add Activity");
        addActivityButton.setEnabled(false);

        back = new JButton("Back");
        back.setEnabled(true);

        // Setting up event listeners
        setupListeners();

        // Adding components to the panel
        panel.add(packageLabel);
        panel.add(packageComboBox);
        panel.add(submitPackageButton);
        panel.add(destinationLabel);
        panel.add(destinationComboBox);
        panel.add(submitDestinationButton);
        panel.add(activityNameLabel);
        panel.add(activityNameField);
        panel.add(activityDescriptionLabel);
        panel.add(activityDescriptionField);
        panel.add(activityCostLabel);
        panel.add(activityCostField);
        panel.add(activityCapacityLabel);
        panel.add(activityCapacityField);
        panel.add(addActivityButton);
        panel.add(back);

        // Adding the panel to the frame
        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Populates the package combo box with available travel packages from the database.
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
     * Sets up event listeners for the submit buttons and the back button.
     */
    private void setupListeners() {
        submitPackageButton.addActionListener(e -> fetchDestinationsForPackage());

        submitDestinationButton.addActionListener(e -> showActivityFields());

        addActivityButton.addActionListener(e -> addActivity());

        back.addActionListener(e -> {
            new DestinationActivity();
            setVisible(false);
        });
    }

    /**
     * Fetches destinations for the selected travel package and enables the submit destination button.
     */
    private void fetchDestinationsForPackage() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        if (selectedPackage != null) {
            try {
                destinationComboBox.removeAllItems();
                String query = "SELECT Destination FROM " + selectedPackage;
                ResultSet resultSet = c.s.executeQuery(query);

                while (resultSet.next()) {
                    destinationComboBox.addItem(resultSet.getString("Destination"));
                }

                resultSet.close();
                submitDestinationButton.setEnabled(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Shows activity fields and enables the add activity button.
     */
    private void showActivityFields() {
        activityNameField.setEnabled(true);
        activityDescriptionField.setEnabled(true);
        activityCostField.setEnabled(true);
        activityCapacityField.setEnabled(true);
        addActivityButton.setEnabled(true);
    }

    /**
     * Adds the activity to the selected destination within the travel package.
     */
    private void addActivity() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        String activityName = activityNameField.getText();
        String activityDescription = activityDescriptionField.getText();
        String activityCost = activityCostField.getText();
        String activityCapacity = activityCapacityField.getText();

        if (selectedPackage != null && selectedDestination != null && !activityName.isEmpty()
                && !activityDescription.isEmpty() && !activityCost.isEmpty() && !activityCapacity.isEmpty()) {
            try {
                // Create destination table if not exists
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + selectedDestination +
                        " (Activity_Name VARCHAR(50), Description VARCHAR(255), Cost DOUBLE, Capacity INT)";
                c.s.executeUpdate(createTableQuery);

                // Insert activity into destination table
                String insertActivityQuery = "INSERT INTO " + selectedDestination +
                        " (Activity_Name, Description, Cost, Capacity) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = c.getConnection().prepareStatement(insertActivityQuery)) {
                    preparedStatement.setString(1, activityName);
                    preparedStatement.setString(2, activityDescription);
                    preparedStatement.setDouble(3, Double.parseDouble(activityCost));
                    preparedStatement.setInt(4, Integer.parseInt(activityCapacity));
                    preparedStatement.executeUpdate();
                }

                JOptionPane.showMessageDialog(this, "Activity added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding activity.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
        }
    }

    /**
     * The main method to start the AddActivity application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddActivity::new);
    }
}
