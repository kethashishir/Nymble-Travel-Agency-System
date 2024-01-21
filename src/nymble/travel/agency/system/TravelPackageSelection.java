
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The TravelPackageSelection class represents a window for selecting a travel package.
 */
public class TravelPackageSelection extends JFrame {

    private JComboBox<String> packageComboBox;
    private JButton selectPackageButton;
    private Conn c = new Conn();

    /**
     * Constructs an instance of TravelPackageSelection.
     */
    public TravelPackageSelection() {
        setTitle("Travel Package Selection");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel selectPackageLabel = new JLabel("Select Travel Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();
        selectPackageButton = new JButton("Select Package");

        panel.add(selectPackageLabel);
        panel.add(packageComboBox);
        panel.add(selectPackageButton);

        setupListeners();

        add(panel, BorderLayout.NORTH);

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
        selectPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPackage = (String) packageComboBox.getSelectedItem();
                if (selectedPackage != null) {
                    JOptionPane.showMessageDialog(TravelPackageSelection.this,
                            "Package selected: " + selectedPackage,
                            "Selected Package",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(TravelPackageSelection.this,
                            "Please select a package.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TravelPackageSelection::new);
    }
}
