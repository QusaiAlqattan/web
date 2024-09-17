package org.example.part3.dao.impl;

import org.example.dao.EnrollmentDAO;
import org.example.models.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map ResultSet to Enrollment object
    private final RowMapper<Enrollment> enrollmentRowMapper = new RowMapper<>() {
        @Override
        public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudentId(rs.getInt("student_id"));
            enrollment.setCourseId(rs.getInt("course_id"));
            enrollment.setGrade(rs.getInt("grade"));
            return enrollment;
        }
    };

    @Override
    public void addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollment (student_id, course_id, grade) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, enrollment.getStudentId(), enrollment.getCourseId(), enrollment.getGrade());
    }

    @Override
    public Enrollment getEnrollment(int studentId, int courseId) {
        String sql = "SELECT * FROM Enrollment WHERE student_id = ? AND course_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{studentId, courseId}, enrollmentRowMapper);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        String sql = "SELECT * FROM Enrollment";
        return jdbcTemplate.query(sql, enrollmentRowMapper);
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE Enrollment SET grade = ? WHERE student_id = ? AND course_id = ?";
        jdbcTemplate.update(sql, enrollment.getGrade(), enrollment.getStudentId(), enrollment.getCourseId());
    }

    @Override
    public void deleteEnrollment(int studentId, int courseId) {
        String sql = "DELETE FROM Enrollment WHERE student_id = ? AND course_id = ?";
        jdbcTemplate.update(sql, studentId, courseId);
    }

    @Override
    public List<Enrollment> getEnrollmentByStudentId(int studentId) {
        String sql = "SELECT * FROM Enrollment WHERE student_id = ?";
        return jdbcTemplate.query(sql, new Object[]{studentId}, enrollmentRowMapper);
    }

    @Override
    public Map<String, Double> getGradeStatisticsByStudentId(int studentId) {
        String sql = "SELECT MAX(grade) AS maxGrade, MIN(grade) AS minGrade, AVG(grade) AS avgGrade FROM Enrollment WHERE student_id = ?";
        return jdbcTemplate.query(sql, new Object[]{studentId}, rs -> {
            Map<String, Double> gradeStats = new HashMap<>();
            if (rs.next()) {
                gradeStats.put("maxGrade", rs.getDouble("maxGrade"));
                gradeStats.put("minGrade", rs.getDouble("minGrade"));
                gradeStats.put("avgGrade", rs.getDouble("avgGrade"));
            }
            return gradeStats;
        });
    }
}
