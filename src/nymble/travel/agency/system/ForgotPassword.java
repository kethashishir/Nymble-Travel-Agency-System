
package nymble.travel.agency.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

/**
 * The ForgotPassword class represents a window for users to retrieve their forgotten passwords.
 */
public class ForgotPassword extends JFrame implements ActionListener {

    private JTextField tfusername, tfname, tfquestion, tfanswer, tfpassword;
    private JButton search, retrieve, back;

    /**
     * Constructs an instance of ForgotPassword.
     */
    public ForgotPassword() {
        setBounds(350, 200, 850, 380);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/forgotpassword.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(580, 70, 200, 200);
        add(image);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(20, 30, 500, 280);
        add(p1);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 25);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(220, 20, 150, 25);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfusername);

        search = new JButton("Search");
        search.setBackground(Color.GRAY);
        search.setForeground(Color.WHITE);
        search.setBounds(380, 20, 100, 25);
        search.addActionListener(this);
        p1.add(search);

        JLabel lbname = new JLabel("Name");
        lbname.setBounds(40, 60, 100, 25);
        lbname.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lbname);

        tfname = new JTextField();
        tfname.setBounds(220, 60, 150, 25);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfname);

        JLabel lbquestion = new JLabel("Security Question");
        lbquestion.setBounds(40, 100, 150, 25);
        lbquestion.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lbquestion);

        tfquestion = new JTextField();
        tfquestion.setBounds(220, 100, 150, 25);
        tfquestion.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfquestion);

        JLabel lbanswer = new JLabel("Enter Answer");
        lbanswer.setBounds(40, 140, 150, 25);
        lbanswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lbanswer);

        tfanswer = new JTextField();
        tfanswer.setBounds(220, 140, 150, 25);
        tfanswer.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfanswer);

        retrieve = new JButton("Retrieve");
        retrieve.setBackground(Color.GRAY);
        retrieve.setForeground(Color.WHITE);
        retrieve.setBounds(380, 140, 100, 25);
        retrieve.addActionListener(this);
        p1.add(retrieve);

        JLabel lbpassword = new JLabel("Password");
        lbpassword.setBounds(40, 180, 150, 25);
        lbpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lbpassword);

        tfpassword = new JTextField();
        tfpassword.setBounds(220, 180, 150, 25);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfpassword);

        back = new JButton("Back");
        back.setBackground(Color.GRAY);
        back.setForeground(Color.WHITE);
        back.setBounds(150, 230, 100, 25);
        back.addActionListener(this);
        p1.add(back);

        setVisible(true);
    }

    /**
     * Handles button clicks and performs corresponding actions.
     *
     * @param ae The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            try {
                String query = "select * from account where username = '" + tfusername.getText() + "'";
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    tfquestion.setText(rs.getString("security"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == retrieve) {
            try {
                String query = "select * from account where answer = '" + tfanswer.getText() + "' AND username = '" + tfusername.getText() + "'";
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    tfpassword.setText(rs.getString("password"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new UserLogin();
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new ForgotPassword();
    }
}
