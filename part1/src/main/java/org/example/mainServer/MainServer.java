package org.example.mainServer;

import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

import org.example.mainServer.ServerToolBox.*;
import org.example.mainServer.ClientHandler.*;

public class MainServer {
    public static void main(String[] args) {
        int PORT = 8080;  // Server port

        String url = "jdbc:mysql://localhost:3306";
        String username = "root"; // Database username
        String password = "12345";  // Database password

        try{
            // Step 1: Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Create the database if it doesn't exist
            ServerToolBox.createDB(url, username, password);

            url += "/mydb";

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
        }

        try(
            ServerSocket serverSocket = new ServerSocket(PORT);
            ExecutorService service = Executors.newCachedThreadPool();
            Connection connection = DriverManager.getConnection(url, username, password)
        ) {
            // Step 3: Accept client connections
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Step 4: Handle client connection with a separate thread
                service.execute(new ClientHandler(socket, url, username, password)); // Pass database info for new connections in the thread
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
