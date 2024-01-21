package nymble.travel.agency.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

/**
 * The AdminLogin class represents the login screen for the admin user.
 * It allows the admin to enter their username and password to access the system.
 */
public class AdminLogin extends JFrame implements ActionListener {

    private JButton login, back;
    private JTextField tfusername, tfpassword;

    /**
     * Constructor for the AdminLogin class.
     * Sets up the GUI components and initializes the frame.
     */
    AdminLogin() {
        setSize(900, 400);
        setLocation(350, 200);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(48, 177, 104));
        p1.setBounds(0, 0, 400, 400);
        p1.setLayout(null);
        add(p1);

        JLabel admin = new JLabel("ADMIN");
        admin.setForeground(Color.WHITE);
        admin.setBounds(145, 30, 200, 30);
        admin.setFont(new Font("Tahoma", Font.BOLD, 30));
        p1.add(admin);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/adminlogin.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(72, 90, 250, 200);
        p1.add(image);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(400, 30, 500, 300);
        add(p2);

        JLabel lblUsername = new JLabel("USERNAME");
        lblUsername.setBounds(60, 20, 150, 25);
        lblUsername.setFont(new Font("SAN SERIF", Font.PLAIN, 20));
        p2.add(lblUsername);

        tfusername = new JTextField();
        tfusername.setBounds(60, 60, 300, 30);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfusername);

        JLabel lblPassword = new JLabel("PASSWORD");
        lblPassword.setBounds(60, 110, 150, 25);
        lblPassword.setFont(new Font("SAN SERIF", Font.PLAIN, 20));
        p2.add(lblPassword);

        tfpassword = new JTextField();
        tfpassword.setBounds(60, 150, 300, 30);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfpassword);

        login = new JButton("LOGIN");
        login.setBounds(60, 200, 130, 30);
        login.setBackground(new Color(48, 177, 104));
        login.setForeground(Color.WHITE);
        login.setBorder(new LineBorder(new Color(48, 177, 104)));
        login.addActionListener(this);
        p2.add(login);

        back = new JButton("Change User");
        back.setBounds(410, 335, 125, 30);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    /**
     * Performs actions based on the button clicked.
     *
     * @param ae ActionEvent object representing the event.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            try {
                String username = tfusername.getText();
                String pass = tfpassword.getText();

                String query = "select * from admin where username = '" + username + "' AND password = '" + pass + "'";
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new AdminLoading(username);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new UserType();
        }
    }

    /**
     * The main method to start the AdminLogin application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new AdminLogin();
    }
}
