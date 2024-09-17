package org.example.dao;

import org.example.models.Role;

import java.util.List;

public interface RoleDAO {
    void addRole(Role role);                       // Add a new role
    Role getRole(int id);                          // Retrieve a role by its ID
    List<Role> getAllRoles();                      // Retrieve all roles
    void updateRole(Role role);                    // Update an existing role
    void deleteRole(int id);                       // Delete a role by its ID
    int getRoleIdByName(String name);
}
