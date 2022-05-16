package dz.hanouti.Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

import java.util.Properties;

public class DbConnect {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hanouti";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // init connection object
    private static Connection connection;

    // init the statement
    private Statement statement;

    private static Properties properties;


    private static Properties getProperties()
    {
        if (properties == null)
        {
            properties = new Properties();

            properties.setProperty("user", USERNAME);

            properties.setProperty("password", PASSWORD);

        }

        return properties;
    }
    public static Connection connect()
    {
        if (connection == null)
        {
            try
            {
                Class.forName(DATABASE_DRIVER);

                connection = (Connection) DriverManager.getConnection(DATABASE_URL,getProperties());
            }
            catch (ClassNotFoundException | SQLException e)
            {
                new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CANCEL).show();
            }
        }
        return connection;
    }

    public static PreparedStatement getPreStmt(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
