
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The TravelPackageManagement class represents the main window for managing travel packages.
 */
public class TravelPackageManagement extends JFrame implements ActionListener {

    private JButton addPackageButton, viewPackageButton, updatePackageButton, deletePackageButton, backButton;

    /**
     * Constructs an instance of TravelPackageManagement.
     */
    TravelPackageManagement() {

        setSize(600, 300);
        setLocation(400, 300);
        setLayout(null);

        addPackageButton = new JButton("Add Package");
        addPackageButton.setBounds(30, 20, 250, 70);
        addPackageButton.setBackground(new Color(48, 177, 104));
        addPackageButton.setForeground(Color.WHITE);
        addPackageButton.setFont(new Font("Tahoma", Font.BOLD, 27));
        addPackageButton.addActionListener(this);
        add(addPackageButton);

        viewPackageButton = new JButton("View Packages");
        viewPackageButton.setBounds(310, 20, 250, 70);
        viewPackageButton.setBackground(new Color(48, 177, 104));
        viewPackageButton.setForeground(Color.WHITE);
        viewPackageButton.setFont(new Font("Tahoma", Font.BOLD, 27));
        viewPackageButton.addActionListener(this);
        add(viewPackageButton);

        updatePackageButton = new JButton("Update Packages");
        updatePackageButton.setBounds(30, 120, 250, 70);
        updatePackageButton.setBackground(new Color(48, 177, 104));
        updatePackageButton.setForeground(Color.WHITE);
        updatePackageButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        updatePackageButton.addActionListener(this);
        add(updatePackageButton);

        deletePackageButton = new JButton("Delete Packages");
        deletePackageButton.setBounds(310, 120, 250, 70);
        deletePackageButton.setBackground(new Color(48, 177, 104));
        deletePackageButton.setForeground(Color.WHITE);
        deletePackageButton.setFont(new Font("Tahoma", Font.BOLD, 27));
        deletePackageButton.addActionListener(this);
        add(deletePackageButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(250, 220, 100, 20);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    /**
     * Handles button clicks.
     *
     * @param ae The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == addPackageButton) {

            new AddPackage();
            setVisible(false);

        } else if (ae.getSource() == viewPackageButton) {
            new ViewPackage();
            setVisible(false);
        } else if (ae.getSource() == updatePackageButton) {
            new UpdatePackage();
            setVisible(false);
        } else if (ae.getSource() == deletePackageButton) {
            new DeletePackage();
            setVisible(false);
        } else {

            setVisible(false);
        }

    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        new TravelPackageManagement();
    }
}
