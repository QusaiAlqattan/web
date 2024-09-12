package org.example.dao;

import org.example.models.Enrollment;

import java.util.List;

public interface EnrollmentDAO {
    void addEnrollment(Enrollment enrollment);      // Add a new enrollment
    Enrollment getEnrollment(int studentId, int courseId); // Retrieve an enrollment
    List<Enrollment> getAllEnrollments();          // Retrieve all enrollments
    void updateEnrollment(Enrollment enrollment);   // Update an existing enrollment
    void deleteEnrollment(int studentId, int courseId); // Delete an enrollment
    List<Enrollment> getEnrollmentByStudentId(int studentId);
}