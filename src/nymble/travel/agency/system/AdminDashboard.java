
package nymble.travel.agency.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class represents the Admin Dashboard for the travel agency system.
 * Allows the admin to navigate to different management and reporting sections.
 */
public class AdminDashboard extends JFrame implements ActionListener {

    String username;
    JButton travelPackageManagement, destinationAndActivity, passengerManagement, activityEnrollment, reports;

    /**
     * Constructor for the AdminDashboard class.
     * Sets up the GUI components and initializes the frame.
     *
     * @param username Username of the admin.
     */
    AdminDashboard(String username) {
        this.username = username;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(48, 177, 104));
        p1.setBounds(0, 0, 1600, 65);
        add(p1);

        JLabel heading = new JLabel("Dashboard");
        heading.setBounds(80, 10, 300, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 50));
        p1.add(heading);

        JLabel admin = new JLabel("Welcome " + username);
        admin.setBounds(1400, 10, 200, 40);
        admin.setForeground(Color.WHITE);
        admin.setFont(new Font("Raleway", Font.PLAIN, 45));
        p1.add(admin);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(Color.GRAY);
        p2.setBounds(0, 65, 1600, 900);
        add(p2);

        travelPackageManagement = new JButton(" Travel Package Management");
        travelPackageManagement.setBounds(0, 0, 320, 40);
        travelPackageManagement.setBackground(Color.WHITE);
        travelPackageManagement.setForeground(new Color(48, 177, 104));
        travelPackageManagement.setFont(new Font("Tahoma", Font.BOLD, 17));
        travelPackageManagement.setMargin(new Insets(0, 30, 0, 30));
        travelPackageManagement.addActionListener(this);
        p2.add(travelPackageManagement);

        destinationAndActivity = new JButton(" Destination & Activity");
        destinationAndActivity.setBounds(320, 0, 320, 40);
        destinationAndActivity.setBackground(Color.WHITE);
        destinationAndActivity.setForeground(new Color(48, 177, 104));
        destinationAndActivity.setFont(new Font("Tahoma", Font.BOLD, 17));
        destinationAndActivity.setMargin(new Insets(0, 30, 0, 30));
        destinationAndActivity.addActionListener(this);
        p2.add(destinationAndActivity);

        passengerManagement = new JButton(" Passenger Management");
        passengerManagement.setBounds(640, 0, 320, 40);
        passengerManagement.setBackground(Color.WHITE);
        passengerManagement.setForeground(new Color(48, 177, 104));
        passengerManagement.setFont(new Font("Tahoma", Font.BOLD, 17));
        passengerManagement.setMargin(new Insets(0, 30, 0, 30));
        passengerManagement.addActionListener(this);
        p2.add(passengerManagement);

        activityEnrollment = new JButton("Activity Enrollment");
        activityEnrollment.setBounds(960, 0, 320, 40);
        activityEnrollment.setBackground(Color.WHITE);
        activityEnrollment.setForeground(new Color(48, 177, 104));
        activityEnrollment.setFont(new Font("Tahoma", Font.BOLD, 17));
        activityEnrollment.setMargin(new Insets(0, 30, 0, 30));
        activityEnrollment.addActionListener(this);
        p2.add(activityEnrollment);

        reports = new JButton(" Reports");
        reports.setBounds(1280, 0, 320, 40);
        reports.setBackground(Color.WHITE);
        reports.setForeground(new Color(48, 177, 104));
        reports.setFont(new Font("Tahoma", Font.BOLD, 17));
        reports.setMargin(new Insets(0, 30, 0, 90));
        reports.addActionListener(this);
        p2.add(reports);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1600, 795, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 40, 1600, 795);
        p2.add(image);

        JLabel text = new JLabel("Nymble Travel Agency System");
        text.setBounds(400, 30, 1200, 70);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Raleway", Font.PLAIN, 55));
        image.add(text);

        setVisible(true);
    }

    /**
     * ActionListener implementation.
     * Handles button clicks and performs appropriate actions.
     *
     * @param ae ActionEvent generated by button click.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == travelPackageManagement) {
            new TravelPackageManagement();
        } else if (ae.getSource() == destinationAndActivity) {
            new DestinationActivity();
        } else if (ae.getSource() == passengerManagement) {
            new PassengerManagement();
        } else if (ae.getSource() == activityEnrollment) {
            new ActivityEnrollment();
        } else if (ae.getSource() == reports) {
            new Reports();
        }
    }

    /**
     * The main method to start the AdminDashboard application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new AdminDashboard("");
    }
}
