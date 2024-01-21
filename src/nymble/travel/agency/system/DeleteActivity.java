
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DeleteActivity class represents the GUI for deleting activities.
 */
public class DeleteActivity extends JFrame {

    private JComboBox<String> packageComboBox;
    private JComboBox<String> destinationComboBox;
    private JComboBox<String> activityComboBox;
    private JButton submitPackageButton, backButton;
    private JButton submitDestinationButton;
    private JButton deleteActivityButton;
    private Conn c = new Conn();

    /**
     * Constructs an instance of DeleteActivity.
     */
    public DeleteActivity() {
        setTitle("Delete Activity");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel packageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();
        submitPackageButton = new JButton("Submit");

        JLabel destinationLabel = new JLabel("Select Destination:");
        destinationComboBox = new JComboBox<>();
        submitDestinationButton = new JButton("Submit");

        JLabel activityLabel = new JLabel("Select Activity:");
        activityComboBox = new JComboBox<>();
        deleteActivityButton = new JButton("Delete Activity");
        backButton = new JButton("Back");

        panel.add(packageLabel);
        panel.add(packageComboBox);
        panel.add(submitPackageButton);
        panel.add(destinationLabel);
        panel.add(destinationComboBox);
        panel.add(submitDestinationButton);
        panel.add(activityLabel);
        panel.add(activityComboBox);
        panel.add(deleteActivityButton);
        panel.add(backButton);

        setupListeners();

        add(panel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Populates the package combo box with data from the database.
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

        deleteActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteActivity();
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
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        if (selectedPackage != null && selectedDestination != null) {
            try {
                activityComboBox.removeAllItems();
                String query = "SELECT Activity_Name FROM " + selectedDestination;
                ResultSet resultSet = c.s.executeQuery(query);

                while (resultSet.next()) {
                    activityComboBox.addItem(resultSet.getString("Activity_Name"));
                }

                resultSet.close();
                deleteActivityButton.setEnabled(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes the selected activity from the database.
     */
    private void deleteActivity() {
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        String selectedActivity = (String) activityComboBox.getSelectedItem();
        if (selectedDestination != null && selectedActivity != null) {
            try {
                String query = "DELETE FROM " + selectedDestination + " WHERE Activity_Name = ?";
                PreparedStatement preparedStatement = c.getConnection().prepareStatement(query);
                preparedStatement.setString(1, selectedActivity);
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Activity deleted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteActivity::new);
    }
}
