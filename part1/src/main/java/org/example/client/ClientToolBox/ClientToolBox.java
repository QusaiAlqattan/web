package org.example.client.ClientToolBox;

import java.io.*;
import java.util.Scanner;

public abstract class ClientToolBox {
    public static void flow(BufferedReader in, BufferedWriter out, Scanner scanner) {
        try {
            while (true) {
                // Step 1: Receive options from the server
                String line;
                String options = "";
                while ((line = in.readLine()) != null) {
                    options = options + line + "\n";
                    if (line.equals("---------------------------------") || line.equals("---------------------------------.")) { // Assuming this is the last line to read
                        break;
                    }
                }

                System.out.print(options); // Display options to the user

                // Read user input from console
                String userInput = scanner.nextLine();

                // If user enters '0', break the outer loop
                if (userInput.equalsIgnoreCase("0")) {
                    return; // Exits the loop
                }

                // send input to the server
                out.write(userInput);
                out.newLine(); // Add newline character for proper formatting
                out.flush();   // Flush the stream to ensure data is sent

                // end flow
                if(line.equals("---------------------------------.")){
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
