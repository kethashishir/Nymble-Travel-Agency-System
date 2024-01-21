package nymble.travel.agency.system;



import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The UserSignup class represents the user signup window in the travel agency system.
 * It allows users to create an account by providing necessary details such as username,
 * name, password, security question, and answer.
 */
public class UserSignup extends JFrame implements ActionListener {
    
    Conn c = new Conn();

    private JButton create, back;
    private JTextField tfUsername, tfName, tfPassword, tfAnswer;
    private Choice security;

    /**
     * Constructor to initialize the UserSignup frame.
     */
    UserSignup() {
        setBounds(350, 200, 900, 360);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Panel for the left side with user details
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(133, 193, 233));
        p1.setBounds(0, 0, 500, 400);
        add(p1);
        p1.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(50, 25, 125, 25);
        p1.add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(190, 20, 180, 25);
        tfUsername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfUsername);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblName.setBounds(50, 60, 125, 25);
        p1.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(190, 60, 180, 25);
        tfName.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfName);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setBounds(50, 100, 125, 25);
        p1.add(lblPassword);

        tfPassword = new JTextField();
        tfPassword.setBounds(190, 100, 180, 25);
        tfPassword.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfPassword);

        JLabel lblSecurity = new JLabel("Security Question");
        lblSecurity.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSecurity.setBounds(50, 140, 125, 25);
        p1.add(lblSecurity);

        security = new Choice();
        security.add("Fav Character From The Boys");
        security.add("Fav Marvel hero");
        security.add("Your Lucky Number");
        security.add("Your childhood superhero");
        security.setBounds(190, 140, 180, 25);
        p1.add(security);

        JLabel lblAnswer = new JLabel("Answer");
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnswer.setBounds(50, 180, 125, 25);
        p1.add(lblAnswer);

        tfAnswer = new JTextField();
        tfAnswer.setBounds(190, 180, 180, 25);
        tfAnswer.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfAnswer);

        // Create and Back buttons
        create = new JButton("Create");
        create.setBackground(Color.WHITE);
        create.setForeground(new Color(133, 193, 233));
        create.setFont(new Font("Tahome", Font.BOLD, 14));
        create.setBounds(80, 250, 100, 30);
        create.addActionListener(this);
        p1.add(create);

        back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setForeground(new Color(133, 193, 233));
        back.setFont(new Font("Tahome", Font.BOLD, 14));
        back.setBounds(250, 250, 100, 30);
        back.addActionListener(this);
        p1.add(back);

        // Panel for the right side with an image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/signup.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(580, 50, 250, 250);
        add(image);

        setVisible(true);
    }

    /**
     * ActionListener implementation for handling button clicks.
     *
     * @param ae The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == create) {
            String username = tfUsername.getText();
            String name = tfName.getText();
            String password = tfPassword.getText();
            String question = security.getSelectedItem();
            String answer = tfAnswer.getText();

            String query = "insert into account values('" + username + "','" + name + "','" + password + "', '" + question + "','" + answer + "')";
            try {
                
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created Successfully");

                setVisible(false);
                new UserLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new UserLogin();
        }

    }

    /**
     * Main method to create an instance of UserSignup.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new UserSignup();
    }
}
