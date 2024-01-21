
package nymble.travel.agency.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The UserDashboard class represents the main dashboard for a user in the travel agency system.
 * It provides buttons for various functionalities such as travel package selection, itinerary view,
 * customer activity enrollment, viewing personal details, and checking available activities.
 */
public class UserDashboard extends JFrame implements ActionListener {

    // Instance variables
    private String username;
    private JButton viewPersonalDetails, travelPackageSelection, ItenaryView, customerActivityEnrollment, availableActivities;

    /**
     * Constructor to initialize the UserDashboard.
     *
     * @param username The username of the user.
     */
    UserDashboard(String username) {

        this.username = username;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        // Panel for header
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.BLUE);
        p1.setBounds(0, 0, 1600, 65);
        add(p1);

        // Heading label
        JLabel heading = new JLabel("Dashboard");
        heading.setBounds(80, 10, 300, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 50));
        p1.add(heading);

        // Welcome message for the user
        JLabel admin = new JLabel("Welcome " + username);
        admin.setBounds(1400, 10, 200, 40);
        admin.setForeground(Color.WHITE);
        heading.setFont(new Font("Raleway", Font.PLAIN, 45));
        p1.add(admin);

        // Panel for buttons and content
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(Color.GRAY);
        p2.setBounds(0, 65, 1600, 900);
        add(p2);

        // Button for travel package selection
        travelPackageSelection = new JButton(" Travel Package Selection");
        travelPackageSelection.setBounds(0, 0, 320, 40);
        travelPackageSelection.setBackground(Color.WHITE);
        travelPackageSelection.setForeground(new Color(48, 177, 104));
        travelPackageSelection.setFont(new Font("Tahoma", Font.BOLD, 17));
        travelPackageSelection.setMargin(new Insets(0, 30, 0, 30));
        travelPackageSelection.addActionListener(this);
        p2.add(travelPackageSelection);

        // Button for itinerary view
        ItenaryView = new JButton("Itinerary View");
        ItenaryView.setBounds(320, 0, 320, 40);
        ItenaryView.setBackground(Color.WHITE);
        ItenaryView.setForeground(new Color(48, 177, 104));
        ItenaryView.setFont(new Font("Tahoma", Font.BOLD, 17));
        ItenaryView.setMargin(new Insets(0, 30, 0, 30));
        ItenaryView.addActionListener(this);
        p2.add(ItenaryView);

        // Button for customer activity enrollment
        customerActivityEnrollment = new JButton(" Activity Enrollment");
        customerActivityEnrollment.setBounds(640, 0, 320, 40);
        customerActivityEnrollment.setBackground(Color.WHITE);
        customerActivityEnrollment.setForeground(new Color(48, 177, 104));
        customerActivityEnrollment.setFont(new Font("Tahoma", Font.BOLD, 17));
        customerActivityEnrollment.setMargin(new Insets(0, 30, 0, 30));
        customerActivityEnrollment.addActionListener(this);
        p2.add(customerActivityEnrollment);

        // Button for viewing personal details
        viewPersonalDetails = new JButton("View Personal Details");
        viewPersonalDetails.setBounds(960, 0, 320, 40);
        viewPersonalDetails.setBackground(Color.WHITE);
        viewPersonalDetails.setForeground(new Color(48, 177, 104));
        viewPersonalDetails.setFont(new Font("Tahoma", Font.BOLD, 17));
        viewPersonalDetails.setMargin(new Insets(0, 30, 0, 30));
        viewPersonalDetails.addActionListener(this);
        p2.add(viewPersonalDetails);

        // Button for checking available activities
        availableActivities = new JButton(" Available Activities:");
        availableActivities.setBounds(1280, 0, 320, 40);
        availableActivities.setBackground(Color.WHITE);
        availableActivities.setForeground(new Color(48, 177, 104));
        availableActivities.setFont(new Font("Tahoma", Font.BOLD, 17));
        availableActivities.setMargin(new Insets(0, 30, 0, 90));
        availableActivities.addActionListener(this);
        p2.add(availableActivities);

        // Image for the dashboard
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1600, 795, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 40, 1600, 795);
        p2.add(image);

        // Text label on the image
        JLabel text = new JLabel("Nymble Travel Agency System");
        text.setBounds(400, 30, 1200, 70);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Raleway", Font.PLAIN, 55));
        image.add(text);

        setVisible(true);
    }

    /**
     * Handles button clicks and performs actions accordingly.
     *
     * @param ae The ActionEvent associated with the button click.
     */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == travelPackageSelection) {
            new TravelPackageSelection();
        } else if (ae.getSource() == ItenaryView) {
            new ItenaryView();
        } else if (ae.getSource() == customerActivityEnrollment) {
            new CustomerActivityEnrollment();
        } else if (ae.getSource() == viewPersonalDetails) {
            new ViewPersonalDetails();
        } else if (ae.getSource() == availableActivities) {
            new AvailableActivities();
        }
    }

    /**
     * Main method to create an instance of UserDashboard.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new UserDashboard("");
    }
}
