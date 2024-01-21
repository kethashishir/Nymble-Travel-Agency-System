
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;

/**
 * The UserLoading class represents a loading screen with a progress bar for a user in the travel agency system.
 * It implements the Runnable interface to run the loading process in a separate thread.
 */
public class UserLoading extends JFrame implements Runnable {

    private Thread t;
    private JProgressBar bar;
    private String username;

    /**
     * Run method to execute the loading process in a separate thread.
     */
    public void run() {
        try {
            for (int i = 1; i <= 101; i++) {
                int max = bar.getMaximum();
                int value = bar.getValue();

                if (value < max) {
                    bar.setValue(bar.getValue() + 1);
                } else {
                    setVisible(false);
                    new UserDashboard(username);
                }
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor to initialize the UserLoading frame.
     *
     * @param username The username of the user.
     */
    UserLoading(String username) {
        this.username = username;

        t = new Thread(this);

        setBounds(500, 200, 650, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Text label for the application
        JLabel text = new JLabel("Travel and Tourism Application");
        text.setBounds(50, 20, 600, 40);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Raleway", Font.BOLD, 35));
        add(text);

        // Progress bar
        bar = new JProgressBar();
        bar.setBounds(150, 100, 300, 35);
        bar.setStringPainted(true);
        add(bar);

        // Loading message label
        JLabel loading = new JLabel("Loading, please wait...");
        loading.setBounds(230, 130, 180, 30);
        loading.setForeground(Color.RED);
        loading.setFont(new Font("Raleway", Font.BOLD, 16));
        add(loading);

        // Welcome message for the user
        JLabel lblUsername = new JLabel("Welcome " + username);
        lblUsername.setBounds(20, 310, 400, 40);
        lblUsername.setForeground(Color.RED);
        lblUsername.setFont(new Font("Raleway", Font.BOLD, 16));
        add(lblUsername);

        t.start();

        setVisible(true);
    }

    /**
     * Main method to create an instance of UserLoading.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new UserLoading("");
    }
}
