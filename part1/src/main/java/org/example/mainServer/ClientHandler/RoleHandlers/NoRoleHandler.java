package org.example.mainServer.ClientHandler.RoleHandlers;

import java.io.*;

public abstract class NoRoleHandler {
    public static void handle(BufferedReader in, BufferedWriter out){
        System.out.println("NoRoleHandler");
    }
}
