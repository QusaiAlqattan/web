package org.example.service;

import org.example.dao.EnrollmentDAO;
import org.example.dao.impl.EnrollmentDAOImpl;
import org.example.models.Enrollment;

import java.io.IOException;

public abstract class TeacherHandler {
    public static void editGrades(String studentId, String courseId, String grade) throws IOException {
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        Enrollment enrollment = new Enrollment();
        enrollment.setGrade(Integer.parseInt(grade));
        enrollment.setStudentId(Integer.parseInt(studentId));
        enrollment.setCourseId(Integer.parseInt(courseId));

        // Add the user to the database
        enrollmentDAO.updateEnrollment(enrollment);
    }
}