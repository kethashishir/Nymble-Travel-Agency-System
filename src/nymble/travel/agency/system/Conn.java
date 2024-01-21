package nymble.travel.agency.system;

import java.sql.*;

/**
 * The Conn class handles the connection to the MySQL database.
 */
public class Conn {

    Connection c;
    Statement s;
    ResultSet rs;

    /**
     * Constructor for the Conn class.
     * Initializes the connection and statement objects.
     */
    Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nymbletravelagency", "root", "Shishirmysql");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the connection object.
     *
     * @return The connection object.
     */
    public Connection getConnection() {
        return c;
    }

}
