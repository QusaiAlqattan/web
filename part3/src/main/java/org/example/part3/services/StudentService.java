package org.example.part3.services;

import org.example.dao.CourseDAO;
import org.example.dao.EnrollmentDAO;
import org.example.dao.UserDAO;
import org.example.models.Enrollment;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final UserDAO userDAO;
    private final EnrollmentDAO enrollmentDAO;
    private final CourseDAO courseDAO;

    @Autowired
    public StudentService(UserDAO userDAO, EnrollmentDAO enrollmentDAO, CourseDAO courseDAO) {
        this.userDAO = userDAO;
        this.enrollmentDAO = enrollmentDAO;
        this.courseDAO = courseDAO;
    }

    // Fetches the current user by username
    public User getCurrentUser(String username) {
        return userDAO.getUserByUsername(username);
    }

    // Retrieves the list of enrollments along with course names and grades for the student
    public List<Map<String, Object>> getEnrollmentDetails(int studentId) {
        List<Enrollment> currentUserEnrollments = enrollmentDAO.getEnrollmentByStudentId(studentId);
        List<Map<String, Object>> enrollmentDetails = new ArrayList<>();

        for (Enrollment enrollment : currentUserEnrollments) {
            String courseName = courseDAO.getCourseNameById(enrollment.getCourseId());

            Map<String, Object> enrollmentMap = new HashMap<>();
            enrollmentMap.put("courseName", courseName);  // Add the course name
            enrollmentMap.put("grade", enrollment.getGrade());  // Add the grade

            enrollmentDetails.add(enrollmentMap);  // Add the map to the list
        }

        return enrollmentDetails;
    }

    // Retrieves grade statistics for the student
    public Map<String, Double> getGradeStatistics(int studentId) {
        return enrollmentDAO.getGradeStatisticsByStudentId(studentId);
    }
}
