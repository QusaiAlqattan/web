package org.example.mainServer.ServerToolBox;

import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class ServerToolBox {
    public static void createDB(String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement stmt = connection.createStatement();

            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS mydb";
            stmt.executeUpdate(createDatabaseSQL);

            // Step 3: Select the newly created or existing database
            stmt.executeUpdate("USE mydb");

            // Step 4: Create the Role table
            String createRoleTableSQL = "CREATE TABLE IF NOT EXISTS Role (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100))";
            stmt.executeUpdate(createRoleTableSQL);

            // Step 5: Create the User table
            String createUserTableSQL = "CREATE TABLE IF NOT EXISTS User (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(100), " +
                    "password VARCHAR(100), " +
                    "role_id INT, " +
                    "FOREIGN KEY (role_id) REFERENCES Role(id))";
            stmt.executeUpdate(createUserTableSQL);

            // Step 6: Create the Course table
            String createCourseTableSQL = "CREATE TABLE IF NOT EXISTS Course (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100), " +
                    "instructor_id INT, " +
                    "FOREIGN KEY (instructor_id) REFERENCES User(id))";
            stmt.executeUpdate(createCourseTableSQL);

            // Step 7: Create the Enrollment table
            String createEnrollmentTableSQL = "CREATE TABLE IF NOT EXISTS Enrollment (" +
                    "student_id INT, " +
                    "course_id INT, " +
                    "grade INT, " +
                    "PRIMARY KEY (student_id, course_id), " +
                    "FOREIGN KEY (student_id) REFERENCES User(id), " +
                    "FOREIGN KEY (course_id) REFERENCES Course(id))";
            stmt.executeUpdate(createEnrollmentTableSQL);
        }catch (SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }
    }
}