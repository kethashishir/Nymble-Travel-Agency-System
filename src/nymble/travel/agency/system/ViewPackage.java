
package nymble.travel.agency.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

/**
 * The ViewPackage class represents a GUI for viewing travel packages and their details.
 * It displays package information in a table, including package name, passenger capacity,
 * available capacity, and associated destinations.
 */
public class ViewPackage extends JFrame implements ActionListener {

    private JTable packageTable;
    private JScrollPane scrollPane;
    private Conn c = new Conn();
    JButton back;

    /**
     * Constructor to initialize the ViewPackage frame.
     */
    ViewPackage() {
        setTitle("Travel Packages");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Package Name");
        columnNames.add("Passenger Capacity");
        columnNames.add("Available Capacity");
        columnNames.add("Destinations");

        Vector<Vector<Object>> data = fetchTravelPackagesFromDatabase();

        packageTable = new JTable(data, columnNames);
        packageTable.setBackground(new Color(48, 177, 104));
        packageTable.setForeground(Color.WHITE);
        scrollPane = new JScrollPane(packageTable);

        back = new JButton("Go Back");
        back.setBackground(new Color(0, 102, 204)); // Set background color for the button
        back.setForeground(Color.WHITE); // Set text color for the button
        back.addActionListener(this);

        // Use a layout manager to arrange components
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Fetches travel packages from the database and returns the data in a Vector<Vector<Object>>.
     *
     * @return A Vector<Vector<Object>> containing the package information.
     */
    public Vector<Vector<Object>> fetchTravelPackagesFromDatabase() {
        Vector<Vector<Object>> data = new Vector<>();

        try {
            PreparedStatement preparedStatement = c.getConnection().prepareStatement("SELECT * FROM Package");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("Package_name"));
                row.add(resultSet.getInt("Passenger_Capacity"));
                row.add(resultSet.getInt("Available_Capacity"));

                Vector<String> destinations = fetchDestinationsForPackage(resultSet.getString("Package_name"));
                row.add(String.join(", ", destinations));

                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Fetches destinations associated with a package from the database and returns them in a Vector.
     *
     * @param packageName The name of the package.
     * @return A Vector<String> containing destination names.
     */
    public Vector<String> fetchDestinationsForPackage(String packageName) {
        Vector<String> destinations = new Vector<>();

        try (ResultSet destinationResultSet = c.s.executeQuery("SELECT Destination FROM " + packageName)) {
            while (destinationResultSet.next()) {
                destinations.add(destinationResultSet.getString("Destination"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    /**
     * Handles action events for the "Go Back" button.
     *
     * @param ae The ActionEvent object.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            new TravelPackageManagement();
            setVisible(false);
        }
    }

    /**
     * Main method to create an instance of ViewPackage using SwingUtilities.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewPackage::new);
    }
}
