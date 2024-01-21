
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * The ViewActivity class represents a GUI for viewing activities based on selected packages and destinations.
 * Users can select a package, fetch destinations, and view activities associated with a selected destination.
 */
public class ViewActivity extends JFrame {

    private JComboBox<String> packageComboBox;
    private JComboBox<String> destinationComboBox;
    private JButton submitPackageButton, submitDestinationButton, backButton;
    private JTable activityTable;
    private JScrollPane scrollPane;
    private Conn c = new Conn();

    /**
     * Constructor to initialize the ViewActivity frame.
     */
    public ViewActivity() {
        setTitle("View Activity");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel packageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();
        submitPackageButton = new JButton("Submit");

        JLabel destinationLabel = new JLabel("Select Destination:");
        destinationComboBox = new JComboBox<>();
        submitDestinationButton = new JButton("Submit");

        backButton = new JButton("Back");

        panel.add(packageLabel);
        panel.add(packageComboBox);
        panel.add(submitPackageButton);
        panel.add(destinationLabel);
        panel.add(destinationComboBox);
        panel.add(submitDestinationButton);
        panel.add(backButton);

        setupListeners();

        add(panel, BorderLayout.NORTH);

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
                displayActivities();
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
     * Displays activities for the selected destination in a table.
     */
    private void displayActivities() {
        String selectedDestination = (String) destinationComboBox.getSelectedItem();
        if (selectedDestination != null) {
            try {
                Vector<String> columnNames = new Vector<>();
                columnNames.add("Activity Name");
                columnNames.add("Description");
                columnNames.add("Cost");
                columnNames.add("Capacity");

                Vector<Vector<Object>> data = new Vector<>();

                String query = "SELECT * FROM " + selectedDestination;
                ResultSet resultSet = c.s.executeQuery(query);

                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(resultSet.getString("Activity_Name"));
                    row.add(resultSet.getString("Description"));
                    row.add(resultSet.getDouble("Cost"));
                    row.add(resultSet.getInt("Capacity"));
                    data.add(row);
                }

                resultSet.close();

                if (scrollPane != null) {
                    remove(scrollPane);
                }

                activityTable = new JTable(data, columnNames);
                scrollPane = new JScrollPane(activityTable);
                add(scrollPane, BorderLayout.CENTER);

                revalidate();
                repaint();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main method to create an instance of ViewActivity using SwingUtilities.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewActivity::new);
    }
}
