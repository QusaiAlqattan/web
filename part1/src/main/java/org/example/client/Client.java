package org.example.client;

import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 8080);){
            // read credentials form the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();

            // create pipelines
            InputStreamReader inputStreamReader =  new InputStreamReader (socket.getInputStream());
            BufferedReader in = new BufferedReader (inputStreamReader);

            OutputStreamWriter outputStreamWriter =  new OutputStreamWriter (socket.getOutputStream());
            BufferedWriter out = new BufferedWriter (outputStreamWriter);

            out.write(username);
            out.newLine();

            out.write(password);
            out.newLine();

            out.flush();

            System.out.println(socket.getInetAddress());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}