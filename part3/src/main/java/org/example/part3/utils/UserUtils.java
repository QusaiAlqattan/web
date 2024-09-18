package org.example.part3.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public abstract class UserUtils {
    public static boolean login(String username, String hashedPassword, Statement stmt) throws SQLException {
        String query = "SELECT * FROM User WHERE username = '" + username + "' AND password = '" + hashedPassword + "'";
        ResultSet resultSet = stmt.executeQuery(query);

        if (resultSet.next()) {
            // User exists
            return true;
        } else {
            // No user was found
            return false;
        }
    }

    public static String getUserRole(String username, String hashedPassword, Statement stmt) throws SQLException {
        // Step 1: Write SQL query with WHERE clause to filter by username and password
        String query = "SELECT Role.name AS role_name FROM User " +
                "JOIN Role ON User.role_id = Role.id " +
                "WHERE User.username = '" + username + "' AND User.password = '" + hashedPassword + "'";

        // Step 2: Execute the query and get the result set
        ResultSet resultSet = stmt.executeQuery(query);

        // Step 3: Check if a row exists and retrieve the role name
        if (resultSet.next()) {
            return resultSet.getString("role_name");  // Return the role name
        } else {
            return null;  // User not found, return null
        }
    }
}
