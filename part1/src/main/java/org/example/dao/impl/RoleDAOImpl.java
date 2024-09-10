package org.example.dao.impl;

import org.example.dao.RoleDAO;
import org.example.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private final String url;
    private final String username;
    private final String password;

    public RoleDAOImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void addRole(Role role) {
        String sql = "INSERT INTO Role (name) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, role.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding role: " + e.getMessage());
        }
    }

    @Override
    public Role getRole(int id) {
        Role role = null;
        String sql = "SELECT * FROM Role WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting role: " + e.getMessage());
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM Role";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all roles: " + e.getMessage());
        }
        return roles;
    }

    @Override
    public void updateRole(Role role) {
        String sql = "UPDATE Role SET name = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, role.getName());
            pstmt.setInt(2, role.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating role: " + e.getMessage());
        }
    }

    @Override
    public void deleteRole(int id) {
        String sql = "DELETE FROM Role WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting role: " + e.getMessage());
        }
    }
}
