
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The UpdatePackage class represents a window for updating travel package details.
 */
public class UpdatePackage extends JFrame {

    private JComboBox<String> packageComboBox;
    private JTextArea detailsTextArea;
    private JTextField capacityField;
    private JTextField destinationsField;
    private Conn c = new Conn();

    /**
     * Constructs an instance of UpdatePackage.
     */
    public UpdatePackage() {
        setTitle("Update Travel Package");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel packageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();

        JLabel detailsLabel = new JLabel("Package Details:");
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);

        JLabel capacityLabel = new JLabel("New Passenger Capacity:");
        capacityField = new JTextField();

        JLabel destinationsLabel = new JLabel("New Destinations (comma-separated):");
        destinationsField = new JTextField();

        JButton updateButton = new JButton("Update Package");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePackage();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TravelPackageManagement();
                dispose(); // Close the current window for simplicity
            }
        });

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(packageLabel)
                .addComponent(detailsLabel)
                .addComponent(capacityLabel)
                .addComponent(destinationsLabel)
                .addComponent(updateButton)
                .addComponent(backButton));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(packageComboBox)
                .addComponent(detailsTextArea)
                .addComponent(capacityField)
                .addComponent(destinationsField));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(packageLabel)
                .addComponent(packageComboBox));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(detailsLabel)
                .addComponent(detailsTextArea));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(capacityLabel)
                .addComponent(capacityField));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(destinationsLabel)
                .addComponent(destinationsField));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(updateButton));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(backButton));
        layout.setVerticalGroup(vGroup);

        add(panel, BorderLayout.CENTER);

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
     * Updates the details of the selected travel package based on user input.
     */
    private void updatePackage() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        if (selectedPackage == null || selectedPackage.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a package.");
            return;
        }

        String newCapacityText = capacityField.getText();
        String newDestinations = destinationsField.getText();

        try {
            // Update Passenger Capacity
            if (!newCapacityText.isEmpty()) {
                int newCapacity = Integer.parseInt(newCapacityText);
                String updateCapacityQuery = "UPDATE Package SET Passenger_Capacity = ? WHERE Package_name = ?";
                try (PreparedStatement preparedStatement = c.getConnection().prepareStatement(updateCapacityQuery)) {
                    preparedStatement.setInt(1, newCapacity);
                    preparedStatement.setString(2, selectedPackage);
                    preparedStatement.executeUpdate();
                }
            }

            // Update Destinations
            if (!newDestinations.isEmpty()) {
                String[] destinationArray = newDestinations.split(",");
                // Delete existing destinations
                String deleteDestinationsQuery = "DELETE FROM " + selectedPackage;
                c.s.executeUpdate(deleteDestinationsQuery);
                // Insert new destinations
                String insertDestinationsQuery = "INSERT INTO " + selectedPackage + " (Destination) VALUES (?)";
                try (PreparedStatement preparedStatement = c.getConnection().prepareStatement(insertDestinationsQuery)) {
                    for (String destination : destinationArray) {
                        preparedStatement.setString(1, destination.trim());
                        preparedStatement.executeUpdate();
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "Package updated successfully!");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating package. Please try again.");
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdatePackage::new);
    }
}
