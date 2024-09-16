package org.example.dao.impl;

import org.example.dao.CourseDAO;
import org.example.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private final String url;
    private final String username;
    private final String password;

    public CourseDAOImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO Course (name, instructor_id) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setInt(2, course.getInstructorId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    @Override
    public Course getCourse(int id) {
        Course course = null;
        String sql = "SELECT * FROM Course WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setInstructorId(rs.getInt("instructor_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting course: " + e.getMessage());
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Course";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setInstructorId(rs.getInt("instructor_id"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all courses: " + e.getMessage());
        }
        return courses;
    }

    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE Course SET name = ?, instructor_id = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setInt(2, course.getInstructorId());
            pstmt.setInt(3, course.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    @Override
    public void deleteCourse(int id) {
        String sql = "DELETE FROM Course WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }

    @Override
    public String getCourseNameById(int courseId) {
        String query = "SELECT name FROM Course WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
