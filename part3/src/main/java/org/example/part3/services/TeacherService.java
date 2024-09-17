package org.example.part3.services;

import org.example.dao.EnrollmentDAO;
import org.example.models.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TeacherService {

    private final EnrollmentDAO enrollmentDAO;

    @Autowired
    public TeacherService(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }

    // Method to edit the grade of a student in a course
    public void editGrades(String studentId, String courseId, String grade) throws IOException {
        // Create an Enrollment object and set the properties
        Enrollment enrollment = new Enrollment();
        enrollment.setGrade(Integer.parseInt(grade));
        enrollment.setStudentId(Integer.parseInt(studentId));
        enrollment.setCourseId(Integer.parseInt(courseId));

        // Update the enrollment in the database
        enrollmentDAO.updateEnrollment(enrollment);
    }
}
