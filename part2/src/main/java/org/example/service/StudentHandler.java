//package org.example.mainServer.ClientHandler.RoleHandlers;
//
//import org.example.mainServer.ClientHandler.RoleHandlers.RoleHandlersToolBox.StudentHandlerToolBox;
//import java.io.*;
//
//public abstract class StudentHandler {
//    public static void handle(BufferedReader in, BufferedWriter out) throws IOException {
//        // send initial options
//        String initialOption = """
//                Please choose one of the following options:
//                1- Show Grades
//                0- Exit
//                ---------------------------------
//                """;
//        out.write(initialOption);
//        out.flush();
//
//        // read user's input
//        String line = in.readLine();
//        switch(line) {
//            case "1":
//                StudentHandlerToolBox.showGrades(in ,out);
//                break;
//            case "0":
//                System.exit(0);
//                break;
//        }
//    }
//}