package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

    // Database connection details
    private final String url = "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC";
    private final String username = "root";
    private final String password = "12345";

    private Connection connection;

    // Method to establish a connection
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Establish connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new SQLException("Failed to connect to the database", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }


    // Method to close the connection
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
