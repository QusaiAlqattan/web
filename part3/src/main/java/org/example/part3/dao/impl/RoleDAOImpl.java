package org.example.part3.dao.impl;

import org.example.dao.RoleDAO;
import org.example.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map ResultSet to Role object
    private final RowMapper<Role> roleRowMapper = new RowMapper<>() {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            return role;
        }
    };

    @Override
    public void addRole(Role role) {
        String sql = "INSERT INTO Role (name) VALUES (?)";
        jdbcTemplate.update(sql, role.getName());
    }

    @Override
    public Role getRole(int id) {
        String sql = "SELECT * FROM Role WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, roleRowMapper);
    }

    @Override
    public List<Role> getAllRoles() {
        String sql = "SELECT * FROM Role";
        return jdbcTemplate.query(sql, roleRowMapper);
    }

    @Override
    public void updateRole(Role role) {
        String sql = "UPDATE Role SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, role.getName(), role.getId());
    }

    @Override
    public void deleteRole(int id) {
        String sql = "DELETE FROM Role WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int getRoleIdByName(String name) {
        String sql = "SELECT id FROM Role WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
    }
}
