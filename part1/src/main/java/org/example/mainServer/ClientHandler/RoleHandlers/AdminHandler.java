package org.example.mainServer.ClientHandler.RoleHandlers;

import org.example.mainServer.ClientHandler.RoleHandlers.RoleHandlersToolBox.AdminHandlerToolBox;
import java.io.*;
import java.security.NoSuchAlgorithmException;

public abstract class AdminHandler {
    public static void handle(BufferedReader in, BufferedWriter out) throws IOException, NoSuchAlgorithmException {
        // send initial options
        String initialOption = """
                Please choose one of the following options:
                2- Manage courses
                1- Manage users
                0- Exit
                ---------------------------------
                """;
        out.write(initialOption);
        out.flush();

        // read user's input
        String line = in.readLine();
        switch(line) {
            case "2":
                AdminHandlerToolBox.manageCourses(in ,out);
                break;
            case "1":
                AdminHandlerToolBox.manageUsers(in ,out);
                break;
            case "0":
                System.exit(0);
                break;
        }
    }
}
