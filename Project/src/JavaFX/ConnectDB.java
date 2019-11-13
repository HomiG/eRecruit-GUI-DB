package JavaFX;

import java.sql.*;


public class ConnectDB {
public Connection connection;

    public Connection getConnection(){

        String dbName = "erecruit";
        String userName = "golden";
        String password = "password";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/erecruit",userName,password);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return connection;
    }

}
