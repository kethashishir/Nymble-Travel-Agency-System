
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The UpdateActivity class represents a window for updating activity details.
 */
public class UpdateActivity extends JFrame {

    private JComboBox<String> packageComboBox;
    private JComboBox<String> destinationComboBox;
    private JComboBox<String> activityComboBox;
    private JTextField updatedDescriptionField;
    private JTextField updatedCostField;
    private JTextField updatedCapacityField;
    private JButton submitPackageButton;
    private JButton submitDestinationButton;
    private JButton submitActivityButton;
    private JButton updateActivityButton;
    private JButton backButton;
    private Conn c = new Conn();

    /**
     * Constructs an instance of UpdateActivity.
     */
    public UpdateActivity() {
        setTitle("Update Activity");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        JLabel packageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();
        submitPackageButton = new JButton("Submit");

        JLabel destinationLabel = new JLabel("Select Destination:");
        destinationComboBox = new JComboBox<>();
        submitDestinationButton = new JButton("Submit");

        JLabel activityLabel = new JLabel("Select Activity:");
        activityComboBox = new JComboBox<>();
        submitActivityButton = new JButton("Submit");

        JLabel updatedDescriptionLabel = new JLabel("Updated Description:");
        updatedDescriptionField = new JTextField();
        updatedDescriptionField.setEnabled(false);

        JLabel updatedCostLabel = new JLabel("Updated Cost:");
        updatedCostField = new JTextField();
        updatedCostField.setEnabled(false);

        JLabel updatedCapacityLabel = new JLabel("Updated Capacity:");
        updatedCapacityField = new JTextField();
        updatedCapacityField.setEnabled(false);

        updateActivityButton = new JButton("Update Activity");
        updateActivityButton.setEnabled(false);

        backButton = new JButton("Back");

        setupListeners();

        panel.add(packageLabel);
        panel.add(packageComboBox);
        panel.add(submitPackageButton);
        panel.add(destinationLabel);
        panel.add(destinationComboBox);
        panel.add(submitDestinationButton);
        panel.add(activityLabel);
        panel.add(activityComboBox);
        panel.add(submitActivityButton);
        panel.add(updatedDescriptionLabel);
        panel.add(updatedDescriptionField);
        panel.add(updatedCostLabel);
        panel.add(updatedCostField);
        panel.add(updatedCapacityLabel);
        panel.add(updatedCapacityField);
        panel.add(updateActivityButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Populates the package combo box with available travel packages.
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
     * Sets up listeners for button clicks.
     */
    private void setupListeners() {
        submitPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchDestinationsForPackage();
            }
        });

        submitDestinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchActivitiesForDestination();
            }
        });

        submitActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateFields();
            }
        });

        updateActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateActivity();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DestinationActivity();
                setVisible(false);
            }
        });
    }

    /**
     * Fetches destinations for the selected package and populates the destination combo box.
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
     * Fetches activities for the selected destination and populates the activity combo box.
     */
    private void fetchActivitiesForDestination() {
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        if (selectedDestination != null) {
            try {
                activityComboBox.removeAllItems();
                String query = "SELECT Activity_Name FROM " + selectedDestination;
                ResultSet resultSet = c.s.executeQuery(query);

                while (resultSet.next()) {
                    activityComboBox.addItem(resultSet.getString("Activity_Name"));
                }

                resultSet.close();
                submitActivityButton.setEnabled(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Enables the update fields for the selected activity.
     */
    private void showUpdateFields() {
        updatedDescriptionField.setEnabled(true);
        updatedCostField.setEnabled(true);
        updatedCapacityField.setEnabled(true);
        updateActivityButton.setEnabled(true);
    }

    /**
     * Updates the details of the selected activity.
     */
    private void updateActivity() {
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        String selectedActivity = (String) activityComboBox.getSelectedItem();
        String updatedDescription = updatedDescriptionField.getText();
        String updatedCost = updatedCostField.getText();
        String updatedCapacity = updatedCapacityField.getText();

        if (selectedDestination != null && selectedActivity != null && !updatedDescription.isEmpty()
                && !updatedCost.isEmpty() && !updatedCapacity.isEmpty()) {
            try {
                String updateActivityQuery = "UPDATE " + selectedDestination +
                        " SET Description = ?, Cost = ?, Capacity = ? WHERE Activity_Name = ?";
                try (PreparedStatement preparedStatement = c.getConnection().prepareStatement(updateActivityQuery)) {
                    preparedStatement.setString(1, updatedDescription);
                    preparedStatement.setDouble(2, Double.parseDouble(updatedCost));
                    preparedStatement.setInt(3, Integer.parseInt(updatedCapacity));
                    preparedStatement.setString(4, selectedActivity);
                    preparedStatement.executeUpdate();
                }

                JOptionPane.showMessageDialog(this, "Activity updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating activity.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdateActivity::new);
    }
}
