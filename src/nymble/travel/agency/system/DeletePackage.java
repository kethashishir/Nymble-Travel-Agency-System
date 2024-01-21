
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DeletePackage class represents the GUI for deleting travel packages.
 */
public class DeletePackage extends JFrame {

    JComboBox<String> packageComboBox;
    Conn c = new Conn();

    /**
     * Constructs an instance of DeletePackage.
     */
    public DeletePackage() {
        setTitle("Delete Travel Package");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel packageLabel = new JLabel("Select Package:");
        packageComboBox = new JComboBox<>();
        populatePackageComboBox();

        JButton deleteButton = new JButton("Delete Package");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePackage();
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
                .addComponent(deleteButton)
                .addComponent(backButton));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(packageComboBox));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(packageLabel)
                .addComponent(packageComboBox));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(deleteButton));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(backButton));
        layout.setVerticalGroup(vGroup);

        add(panel, BorderLayout.CENTER);

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
     * Deletes the selected travel package from the database.
     */
    private void deletePackage() {
        String selectedPackage = (String) packageComboBox.getSelectedItem();
        if (selectedPackage != null) {
            try {
                // Delete entry from Package table
                String deletePackageQuery = "DELETE FROM Package WHERE Package_name = ?";
                try (PreparedStatement deletePackageStatement = c.getConnection().prepareStatement(deletePackageQuery)) {
                    deletePackageStatement.setString(1, selectedPackage);
                    deletePackageStatement.executeUpdate();
                }

                // Drop corresponding table
                String dropTableQuery = "DROP TABLE IF EXISTS " + selectedPackage;
                try (PreparedStatement dropTableStatement = c.getConnection().prepareStatement(dropTableQuery)) {
                    dropTableStatement.executeUpdate();
                }

                JOptionPane.showMessageDialog(this, "Package deleted successfully.");
                setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting package.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a package to delete.");
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeletePackage::new);
    }
}
