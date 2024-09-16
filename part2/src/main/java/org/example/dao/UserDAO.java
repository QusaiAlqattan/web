package org.example.dao;

import org.example.models.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);                       // Add a new user
    User getUser(int id);                          // Retrieve a user by its ID
    List<User> getAllUsers();                      // Retrieve all users
    void updateUser(User user);                    // Update an existing user
    void deleteUser(int id);                       // Delete a user by its ID
    User getUserByUsername(String username);
}
