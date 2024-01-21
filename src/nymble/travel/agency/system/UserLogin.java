
package nymble.travel.agency.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

/**
 * The UserLogin class represents the login window for users in the travel agency system.
 * It allows users to enter their username and password to log in.
 */
public class UserLogin extends JFrame implements ActionListener {

    private JButton login, signup, password;
    private JTextField tfUsername, tfPassword;

    /**
     * Constructor to initialize the UserLogin frame.
     */
    UserLogin() {
        setSize(900, 400);
        setLocation(350, 200);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        // Panel for the left side with an image
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(131, 193, 233));
        p1.setBounds(0, 0, 400, 400);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(100, 120, 200, 200);
        p1.add(image);

        // Panel for the right side with login components
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(400, 30, 450, 300);
        add(p2);

        JLabel lblUsername = new JLabel("USERNAME");
        lblUsername.setBounds(60, 20, 150, 25);
        lblUsername.setFont(new Font("SAN SERIF", Font.PLAIN, 20));
        p2.add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(60, 60, 300, 30);
        tfUsername.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfUsername);

        JLabel lblPassword = new JLabel("PASSWORD");
        lblPassword.setBounds(60, 110, 150, 25);
        lblPassword.setFont(new Font("SAN SERIF", Font.PLAIN, 20));
        p2.add(lblPassword);

        tfPassword = new JTextField();
        tfPassword.setBounds(60, 150, 300, 30);
        tfPassword.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfPassword);

        // Login button
        login = new JButton("LOGIN");
        login.setBounds(60, 200, 130, 30);
        login.setBackground(new Color(133, 193, 233));
        login.setForeground(Color.WHITE);
        login.setBorder(new LineBorder(new Color(133, 193, 233)));
        login.addActionListener(this);
        p2.add(login);

        // Signup button
        signup = new JButton("SIGNUP");
        signup.setBounds(230, 200, 130, 30);
        signup.setBackground(new Color(133, 193, 233));
        signup.setForeground(Color.WHITE);
        signup.setBorder(new LineBorder(new Color(133, 193, 233)));
        signup.addActionListener(this);
        p2.add(signup);

        // Forget password button
        password = new JButton("FORGET PASSWORD");
        password.setBounds(130, 250, 180, 30);
        password.setBackground(new Color(133, 193, 233));
        password.setForeground(Color.WHITE);
        password.setBorder(new LineBorder(new Color(133, 193, 233)));
        password.addActionListener(this);
        p2.add(password);

        // Trouble in login message
        JLabel text = new JLabel("Trouble in login..");
        text.setBounds(300, 250, 150, 20);
        text.setForeground(Color.RED);
        p2.add(text);

        setVisible(true);
    }

    /**
     * ActionListener implementation for handling button clicks.
     *
     * @param ae The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            try {
                String username = tfUsername.getText();
                String password = tfPassword.getText();

                String query = "select * from account where username = '" + username + "' AND password = '" + password + "'";
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new UserLoading(username);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new UserSignup();
        } else {
            setVisible(false);
            new ForgotPassword();
        }
    }

    /**
     * Main method to create an instance of UserLogin.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new UserLogin();
    }
}
