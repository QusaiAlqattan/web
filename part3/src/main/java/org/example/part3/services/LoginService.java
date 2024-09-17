package org.example.part3.services;

import org.example.part3.utils.*;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;

@Service
public class LoginService {

    private final Statement stmt;

    public LoginService() throws Exception {
        DataBaseManager dbManager = new DataBaseManager();
        Connection connection = dbManager.getConnection();
        this.stmt = connection.createStatement();
    }

    public boolean authenticateUser(String username, String password) throws Exception {
        String hashedPassword = PasswordUtil.hashPassword(password);
        return UserUtils.login(username, hashedPassword, stmt);
    }

    public String getUserRole(String username, String password) throws Exception {
        String hashedPassword = PasswordUtil.hashPassword(password);
        return UserUtils.getUserRole(username, hashedPassword, stmt);
    }
}
