
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * The Reports class represents a window for generating reports.
 */
public class Reports extends JFrame {

    private JComboBox<String> packageComboBox;
    private JComboBox<String> passengerComboBox;
    private JTextArea reportTextArea;
    private JButton itineraryButton;
    private JButton passengerListButton;
    private JButton individualPassengerButton;
    private Conn c = new Conn();

    /**
     * Constructs an instance of Reports.
     */
    public Reports() {
        setTitle("Reports");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel selectPackageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();

        JLabel selectPassengerLabel = new JLabel("Select Passenger:");
        passengerComboBox = new JComboBox<>();
        populatePassengerComboBox();

        itineraryButton = new JButton("Print Itinerary");
        passengerListButton = new JButton("Print Passenger List");
        individualPassengerButton = new JButton("Print Individual Passenger Details");

        reportTextArea = new JTextArea();
        JScrollPane textScrollPane = new JScrollPane(reportTextArea);

        panel.add(selectPackageLabel);
        panel.add(packageComboBox);
        panel.add(selectPassengerLabel);
        panel.add(passengerComboBox);
        panel.add(itineraryButton);
        panel.add(passengerListButton);
        panel.add(individualPassengerButton);
        panel.add(textScrollPane);

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
     * Populates the passenger combo box with available passengers.
     */
    private void populatePassengerComboBox() {
        try {
            String query = "SELECT Passenger_name FROM Passenger";
            ResultSet resultSet = c.s.executeQuery(query);

            while (resultSet.next()) {
                passengerComboBox.addItem(resultSet.getString("Passenger_name"));
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
        itineraryButton.addActionListener(e -> printItinerary());
        passengerListButton.addActionListener(e -> printPassengerList());
        individualPassengerButton.addActionListener(e -> printIndividualPassengerDetails());
    }

    /**
     * Prints the itinerary for the selected travel package.
     */
    private void printItinerary() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        if (selectedPackage != null) {
            // Implement logic to print itinerary for the selected travel package
            reportTextArea.setText("Itinerary for " + selectedPackage);
        }
    }

    /**
     * Prints the passenger list for the selected travel package.
     */
    private void printPassengerList() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        if (selectedPackage != null) {
            // Implement logic to print passenger list for the selected travel package
            reportTextArea.setText("Passenger list for " + selectedPackage);
        }
    }

    /**
     * Prints details of the selected individual passenger.
     */
    private void printIndividualPassengerDetails() {
        String selectedPassenger = (String) passengerComboBox.getSelectedItem();
        if (selectedPassenger != null) {
            // Implement logic to print details of the selected individual passenger
            reportTextArea.setText("Details for " + selectedPassenger);
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Reports::new);
    }
}
