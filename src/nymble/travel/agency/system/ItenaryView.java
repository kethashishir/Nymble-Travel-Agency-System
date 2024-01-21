
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The ItenaryView class represents a window for viewing travel itineraries.
 */
public class ItenaryView extends JFrame {

    private JTextArea itineraryTextArea;
    private JButton viewItineraryButton;
    private JComboBox<String> packageComboBox;
    private Conn c = new Conn();

    /**
     * Constructs an instance of ItenaryView.
     */
    public ItenaryView() {
        setTitle("Itinerary View");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JLabel selectPackageLabel = new JLabel("Select Travel Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();
        viewItineraryButton = new JButton("View Itinerary");

        topPanel.add(selectPackageLabel);
        topPanel.add(packageComboBox);

        panel.add(topPanel, BorderLayout.NORTH);

        itineraryTextArea = new JTextArea();
        itineraryTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(itineraryTextArea);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(viewItineraryButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

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
     * Sets up action listeners for buttons.
     */
    private void setupListeners() {
        viewItineraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPackage = (String) packageComboBox.getSelectedItem();
                if (selectedPackage != null) {
                    displayItinerary(selectedPackage);
                } else {
                    JOptionPane.showMessageDialog(ItenaryView.this,
                            "Please select a package.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Displays the itinerary for the selected travel package.
     *
     * @param packageName The selected travel package.
     */
    private void displayItinerary(String packageName) {
        try {
            String itineraryQuery = "SELECT * FROM Itinerary WHERE Package_name = '" + packageName + "'";
            ResultSet resultSet = c.s.executeQuery(itineraryQuery);

            StringBuilder itineraryText = new StringBuilder();

            while (resultSet.next()) {
                itineraryText.append("Destination: ").append(resultSet.getString("Destination")).append("\n");

                String activitiesQuery = "SELECT * FROM " + packageName + " WHERE Destination = '"
                        + resultSet.getString("Destination") + "'";
                ResultSet activitiesResultSet = c.s.executeQuery(activitiesQuery);

                while (activitiesResultSet.next()) {
                    itineraryText.append("  Activity: ").append(activitiesResultSet.getString("Activity_name"))
                            .append(", Description: ").append(activitiesResultSet.getString("Description"))
                            .append(", Cost: ").append(activitiesResultSet.getDouble("Cost"))
                            .append(", Capacity: ").append(activitiesResultSet.getInt("Capacity"))
                            .append("\n");
                }

                activitiesResultSet.close();
                itineraryText.append("\n");
            }

            resultSet.close();
            itineraryTextArea.setText(itineraryText.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ItenaryView::new);
    }
}
