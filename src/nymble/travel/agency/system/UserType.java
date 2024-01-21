
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The UserType class represents the user type selection window in the travel agency system.
 * Users can choose between Admin and Customer logins.
 */
public class UserType extends JFrame implements ActionListener {

    private JButton admin, customer;

    /**
     * Constructor to initialize the UserType frame.
     */
    UserType() {
        setSize(900, 400);
        setLocation(350, 200);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        // Panel for the header
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(48, 177, 104));
        p1.setBounds(0, 0, 900, 50);
        p1.setLayout(null);
        add(p1);

        JLabel userTypeLabel = new JLabel("Select User");
        userTypeLabel.setBounds(350, 10, 200, 30);
        userTypeLabel.setFont(new Font("Raleway", Font.BOLD, 30));
        userTypeLabel.setForeground(Color.WHITE);
        p1.add(userTypeLabel);

        // Panel for Admin Login
        JPanel p2 = new JPanel();
        p2.setBackground(Color.WHITE);
        p2.setBounds(0, 50, 450, 350);
        p2.setLayout(null);
        add(p2);

        ImageIcon adminLogoIcon = new ImageIcon(ClassLoader.getSystemResource("icons/agencylogo.png"));
        Image adminLogoImage = adminLogoIcon.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        ImageIcon adminLogo = new ImageIcon(adminLogoImage);
        JLabel adminImage = new JLabel(adminLogo);
        adminImage.setBounds(100, 30, 200, 200);
        p2.add(adminImage);

        admin = new JButton("Agency Login");
        admin.setBackground(new Color(48, 177, 104));
        admin.setForeground(Color.WHITE);
        admin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        admin.setBounds(120, 250, 175, 25);
        admin.addActionListener(this);
        p2.add(admin);

        // Panel for Customer Login
        JPanel p3 = new JPanel();
        p3.setBackground(Color.WHITE);
        p3.setBounds(450, 50, 450, 350);
        p3.setLayout(null);
        add(p3);

        ImageIcon customerLogoIcon = new ImageIcon(ClassLoader.getSystemResource("icons/customerlogo.png"));
        Image customerLogoImage = customerLogoIcon.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT);
        ImageIcon customerLogo = new ImageIcon(customerLogoImage);
        JLabel customerImage = new JLabel(customerLogo);
        customerImage.setBounds(100, 30, 200, 200);
        p3.add(customerImage);

        customer = new JButton("Customer Login");
        customer.setBackground(new Color(48, 177, 104));
        customer.setForeground(Color.WHITE);
        customer.setFont(new Font("Tahoma", Font.PLAIN, 20));
        customer.setBounds(120, 250, 175, 25);
        customer.addActionListener(this);
        p3.add(customer);

        setVisible(true);
    }

    /**
     * ActionListener implementation for handling button clicks.
     *
     * @param ae The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == customer) {
            setVisible(false);
            new UserLogin();
        } else {
            setVisible(false);
            new AdminLogin();
        }
    }

    /**
     * Main method to create an instance of UserType.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new UserType();
    }
}
