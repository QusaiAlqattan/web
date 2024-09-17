package org.example.part3.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Step 1: Get an instance of the SHA-256 MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Step 2: Hash the password
        byte[] hashBytes = md.digest(password.getBytes());

        // Step 3: Convert the byte array into a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();  // Return the hashed password in hexadecimal form
    }
}
