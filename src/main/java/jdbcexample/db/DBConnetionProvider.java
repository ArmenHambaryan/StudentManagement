package jdbcexample.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnetionProvider {
    private static DBConnetionProvider instance = new DBConnetionProvider();

    private Connection connection;
    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL="jdbc:mysql://localhost:3306/user";
    private final String USERNAME="root";
    private final String PASSWORD="root";


    private DBConnetionProvider() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnetionProvider getInstance() {

        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()){
    connection= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
