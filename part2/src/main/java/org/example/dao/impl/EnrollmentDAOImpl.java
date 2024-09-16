package org.example.dao.impl;

import org.example.dao.EnrollmentDAO;
import org.example.models.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {
    private final String url;
    private final String username;
    private final String password;

    public EnrollmentDAOImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollment (student_id, course_id, grade) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getStudentId());
            pstmt.setInt(2, enrollment.getCourseId());
            pstmt.setInt(3, enrollment.getGrade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding enrollment: " + e.getMessage());
        }
    }

    @Override
    public Enrollment getEnrollment(int studentId, int courseId) {
        Enrollment enrollment = null;
        String sql = "SELECT * FROM Enrollment WHERE student_id = ? AND course_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                enrollment = new Enrollment();
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setGrade(rs.getInt("grade"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting enrollment: " + e.getMessage());
        }
        return enrollment;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setGrade(rs.getInt("grade"));
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all enrollments: " + e.getMessage());
        }
        return enrollments;
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE Enrollment SET grade = ? WHERE student_id = ? AND course_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getGrade());
            pstmt.setInt(2, enrollment.getStudentId());
            pstmt.setInt(3, enrollment.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating enrollment: " + e.getMessage());
        }
    }

    @Override
    public void deleteEnrollment(int studentId, int courseId) {
        String sql = "DELETE FROM Enrollment WHERE student_id = ? AND course_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting enrollment: " + e.getMessage());
        }
    }

    @Override
    public List<Enrollment> getEnrollmentByStudentId(int studentId) {
        String sql = "SELECT course_id, grade FROM Enrollment WHERE student_id = ?";
        List<Enrollment> enrollments = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                int grade = rs.getInt("grade");

                Enrollment enrollment = new Enrollment();
                enrollment.setCourseId(courseId);
                enrollment.setGrade(grade);

                enrollments.add(enrollment);

                System.out.println("Enrollment " + enrollment.getCourseId() + " " + enrollment.getGrade());
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving enrollments for student ID: " + e.getMessage());
        }

        return enrollments;
    }
}
