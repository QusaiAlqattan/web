package org.example.part3.services;

import org.example.dao.CourseDAO;
import org.example.dao.EnrollmentDAO;
import org.example.dao.RoleDAO;
import org.example.dao.UserDAO;
import org.example.models.Course;
import org.example.models.Enrollment;
import org.example.models.Role;
import org.example.models.User;
import org.example.part3.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class AdminService {

    private final RoleDAO roleDAO;
    private final UserDAO userDAO;
    private final CourseDAO courseDAO;
    private final EnrollmentDAO enrollmentDAO;

    @Autowired
    public AdminService(RoleDAO roleDAO, UserDAO userDAO, CourseDAO courseDAO, EnrollmentDAO enrollmentDAO) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.courseDAO = courseDAO;
        this.enrollmentDAO = enrollmentDAO;
    }

    public void createUser(String username, String role) throws NoSuchAlgorithmException {
        // Find the role by name
        Optional<Role> wantedRole = roleDAO.getAllRoles().stream()
                .filter(r -> r.getName().equalsIgnoreCase(role))
                .findFirst();

        if (wantedRole.isPresent()) {
            // Create the User object
            User user = new User();
            user.setUsername(username);
            user.setPassword(generatePassword());
            user.setRoleId(wantedRole.get().getId()); // Set the Role ID

            // Add the user to the database
            userDAO.addUser(user);
        } else {
            System.out.println("Failed to create user because the role was not found.");
        }
    }

    public String generatePassword() throws NoSuchAlgorithmException {
        String password = "12345";  // You may want to use a better password generation method.
        return PasswordUtil.hashPassword(password);
    }

    public void createCourse(String name, String instructorId) {
        // Create the Course object
        Course course = new Course();
        course.setName(name);
        course.setInstructorId(Integer.parseInt(instructorId));

        // Add the course to the database
        courseDAO.addCourse(course);
    }

    public void addStudent(String studentId, String courseId) {
        // Create the Enrollment object
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(Integer.parseInt(studentId));
        enrollment.setCourseId(Integer.parseInt(courseId));

        // Add the enrollment to the database
        enrollmentDAO.addEnrollment(enrollment);
    }
}
