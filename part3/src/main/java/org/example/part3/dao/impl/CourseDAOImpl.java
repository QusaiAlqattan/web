package org.example.part3.dao.impl;

import org.example.dao.CourseDAO;
import org.example.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map ResultSet to Course object
    private final RowMapper<Course> courseRowMapper = new RowMapper<>() {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setInstructorId(rs.getInt("instructor_id"));
            return course;
        }
    };

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO Course (name, instructor_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, course.getName(), course.getInstructorId());
    }

    @Override
    public Course getCourse(int id) {
        String sql = "SELECT * FROM Course WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, courseRowMapper);
    }

    @Override
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM Course";
        return jdbcTemplate.query(sql, courseRowMapper);
    }

    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE Course SET name = ?, instructor_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, course.getName(), course.getInstructorId(), course.getId());
    }

    @Override
    public void deleteCourse(int id) {
        String sql = "DELETE FROM Course WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public String getCourseNameById(int courseId) {
        String sql = "SELECT name FROM Course WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{courseId}, String.class);
    }
}
