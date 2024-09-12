package org.example.mainServer.ClientHandler;

import org.example.mainServer.ClientHandler.ClientHandlerToolBox.*;
import org.example.mainServer.ClientHandler.RoleHandlers.*;
import java.sql.*;
import java.net.*;
import java.io.*;

public class ClientHandler extends Thread {
    private Socket socket;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    public ClientHandler(Socket socket, String url, String username, String password) {
        this.socket = socket;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {

            // Create a new Statement (one for each client/thread)
            Statement stmt = connection.createStatement();

            // build streams
            InputStreamReader inputStreamReader =  new InputStreamReader (socket.getInputStream());
            BufferedReader in = new BufferedReader (inputStreamReader);

            OutputStreamWriter outputStreamWriter =  new OutputStreamWriter (socket.getOutputStream());
            BufferedWriter out = new BufferedWriter (outputStreamWriter);


            // get credentials
            String username = in.readLine();
            String hashedPassword = ClientHandlerToolBox.hashPassword(in.readLine());

            // login and get role
            String role = "";
            if (ClientHandlerToolBox.login(username, hashedPassword, stmt)){
                role = ClientHandlerToolBox.getUserRole(username, hashedPassword, stmt);
            }

            switch (role){
                case "Admin":
                    AdminHandler.handle(in ,out);
                    break;
                case "Teacher":
                    TeacherHandler.handle(in ,out);
                    break;
                case "Student":
                    StudentHandler.handle(in ,out);
                    break;
                default:
                    NoRoleHandler.handle(in ,out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // Close resources like socket and statement
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}