
package nymble.travel.agency.system;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the loading screen for the admin application.
 * Displays a progress bar while initializing the application.
 */
public class AdminLoading extends JFrame implements Runnable {

    private Thread thread;
    private JProgressBar progressBar;
    private String username;

    /**
     * Implementation of the run method for the Runnable interface.
     * Controls the progress bar loading and hides the loading screen when done.
     */
    public void run() {
        try {
            for (int i = 1; i <= 101; i++) {
                int max = progressBar.getMaximum();
                int value = progressBar.getValue();

                if (value < max) {
                    progressBar.setValue(progressBar.getValue() + 1);
                } else {
                    setVisible(false);
                    // Uncomment the following line to proceed to the next screen
                    new AdminDashboard(username);
                }
                Thread.sleep(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for the AdminLoading class.
     *
     * @param username Username of the admin.
     */
    AdminLoading(String username) {
        this.username = username;
        thread = new Thread(this);

        setBounds(500, 200, 650, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("NYMBLE TRAVEL AGENCY");
        text.setBounds(70, 20, 600, 40);
        text.setForeground(new Color(48, 177, 104));
        text.setFont(new Font("Raleway", Font.BOLD, 35));
        add(text);

        progressBar = new JProgressBar();
        progressBar.setBounds(150, 100, 300, 35);
        progressBar.setStringPainted(true);
        add(progressBar);

        JLabel loading = new JLabel("Loading, please wait...");
        loading.setBounds(230, 130, 180, 30);
        loading.setForeground(Color.BLACK);
        loading.setFont(new Font("Raleway", Font.BOLD, 16));
        add(loading);

        JLabel lblUsername = new JLabel("Welcome " + username);
        lblUsername.setBounds(20, 310, 400, 40);
        lblUsername.setForeground(new Color(48, 177, 104));
        lblUsername.setFont(new Font("Raleway", Font.BOLD, 16));
        add(lblUsername);

        thread.start();

        setVisible(true);
    }

    /**
     * The main method to start the AdminLoading application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new AdminLoading("");
    }
}
