
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * The ViewPersonalDetails class represents a GUI for viewing personal details of passengers.
 * It allows selecting a passenger and displays their name, passenger number, balance, and activities signed up for.
 */
public class ViewPersonalDetails extends JFrame {

    private JComboBox<String> passengerComboBox;
    private JTextArea detailsTextArea;
    private JButton viewDetailsButton;
    private Conn c = new Conn();

    /**
     * Constructor to initialize the ViewPersonalDetails frame.
     */
    public ViewPersonalDetails() {
        setTitle("View Personal Details");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel selectPassengerLabel = new JLabel("Select Passenger:");
        passengerComboBox = new JComboBox<>();
        populatePassengerComboBox();

        viewDetailsButton = new JButton("View Details");

        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);

        panel.add(selectPassengerLabel);
        panel.add(passengerComboBox);
        panel.add(viewDetailsButton);
        panel.add(scrollPane);

        setupListeners();

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Populates the passengerComboBox with passenger names from the database.
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
        viewDetailsButton.addActionListener(e -> viewPersonalDetails());
    }

    /**
     * Fetches and displays personal details of the selected passenger.
     */
    private void viewPersonalDetails() {
        String selectedPassenger = (String) passengerComboBox.getSelectedItem();

        if (selectedPassenger != null) {
            try {
                String query = "SELECT * FROM Passenger WHERE Passenger_name = '" + selectedPassenger + "'";
                ResultSet resultSet = c.s.executeQuery(query);

                if (resultSet.next()) {
                    String details = "Name: " + resultSet.getString("Passenger_name") + "\n" +
                            "Passenger Number: " + resultSet.getString("Passenger_number") + "\n" +
                            "Balance: " + resultSet.getString("Balance") + "\n" +
                            "Activities Signed Up For: " + getActivitiesForPassenger(resultSet.getString("Passenger_number"));
                    detailsTextArea.setText(details);
                }

                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Retrieves and returns activities signed up for by the passenger.
     *
     * @param passengerNumber The passenger number.
     * @return A string containing activities signed up for.
     */
    private String getActivitiesForPassenger(String passengerNumber) {
        StringBuilder activities = new StringBuilder();

        try {
            String query = "SELECT Activity_name, Destination FROM Enrollment WHERE Passenger_number = '" + passengerNumber + "'";
            ResultSet resultSet = c.s.executeQuery(query);

            while (resultSet.next()) {
                activities.append("\n- ").append(resultSet.getString("Activity_name")).append(" at ")
                        .append(resultSet.getString("Destination"));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activities.toString();
    }

    /**
     * Main method to create an instance of ViewPersonalDetails using SwingUtilities.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewPersonalDetails::new);
    }
}
