
package nymble.travel.agency.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The DestinationActivity class represents the main menu for managing destination activities.
 */
public class DestinationActivity extends JFrame implements ActionListener {

    private JButton addActivityButton, viewActivityButton, updateActivityButton, deleteActivityButton, backButton;

    /**
     * Constructs an instance of DestinationActivity.
     */
    public DestinationActivity() {
        setSize(600, 300);
        setLocation(400, 300);
        setLayout(null);

        addActivityButton = new JButton("Add Activity");
        addActivityButton.setBounds(30, 20, 250, 70);
        addActivityButton.setBackground(new Color(48, 177, 104));
        addActivityButton.setForeground(Color.WHITE);
        addActivityButton.setFont(new Font("Tahoma", Font.BOLD, 27));
        addActivityButton.addActionListener(this);
        add(addActivityButton);

        viewActivityButton = new JButton("View Activities");
        viewActivityButton.setBounds(310, 20, 250, 70);
        viewActivityButton.setBackground(new Color(48, 177, 104));
        viewActivityButton.setForeground(Color.WHITE);
        viewActivityButton.setFont(new Font("Tahoma", Font.BOLD, 27));
        viewActivityButton.addActionListener(this);
        add(viewActivityButton);

        updateActivityButton = new JButton("Update Activity");
        updateActivityButton.setBounds(30, 120, 250, 70);
        updateActivityButton.setBackground(new Color(48, 177, 104));
        updateActivityButton.setForeground(Color.WHITE);
        updateActivityButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        updateActivityButton.addActionListener(this);
        add(updateActivityButton);

        deleteActivityButton = new JButton("Delete Packages");
        deleteActivityButton.setBounds(310, 120, 250, 70);
        deleteActivityButton.setBackground(new Color(48, 177, 104));
        deleteActivityButton.setForeground(Color.WHITE);
        deleteActivityButton.setFont(new Font("Tahoma", Font.BOLD, 27));
        deleteActivityButton.addActionListener(this);
        add(deleteActivityButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(250, 220, 100, 20);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    /**
     * Handles button clicks and performs corresponding actions.
     *
     * @param ae The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addActivityButton) {
            new AddActivity();
            setVisible(false);
        } else if (ae.getSource() == viewActivityButton) {
            new ViewActivity();
            setVisible(false);
        } else if (ae.getSource() == updateActivityButton) {
            new UpdateActivity();
            setVisible(false);
        } else if (ae.getSource() == deleteActivityButton) {
            new DeleteActivity();
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
        new DestinationActivity();
    }
}
