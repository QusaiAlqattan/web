package org.example.service;

import org.example.dao.CourseDAO;
import org.example.dao.EnrollmentDAO;
import org.example.dao.RoleDAO;
import org.example.dao.UserDAO;
import org.example.dao.impl.CourseDAOImpl;
import org.example.dao.impl.EnrollmentDAOImpl;
import org.example.dao.impl.RoleDAOImpl;
import org.example.dao.impl.UserDAOImpl;
import org.example.models.Course;
import org.example.models.Enrollment;
import org.example.models.Role;
import org.example.models.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.example.mainServer.ClientHandler.RoleHandlers.LoginHandler.hashPassword;

public abstract class AdminHandler {
    public static void createUser(String username, String role) throws NoSuchAlgorithmException{
        // Assuming roleName is provided as "Admin"
        RoleDAO roleDAO = new RoleDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");
        Role wantedRole = null;
        List<Role> roles = roleDAO.getAllRoles();
        for (Role r : roles) {
            if (r.getName().equalsIgnoreCase(role)) {
                wantedRole = r;
                break;
            }
        }

        if (wantedRole != null) {
            UserDAO userDAO = new UserDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

            // Create the User object
            User user = new User();
            user.setUsername(username);
            user.setPassword(generatePassword());
            user.setRoleId(wantedRole.getId()); // Set the Role ID

            // Add the user to the database
            userDAO.addUser(user);
        } else {
            System.out.println("Failed to create user because the role was not found.");
        }
    }

    public static String generatePassword() throws NoSuchAlgorithmException {
        String password = "12345";
        return hashPassword(password);
    }

    public static void createCourse(String name, String instructorId) {
        // create an instance
        CourseDAO courseDAO = new CourseDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        // Create the Course object
        Course course = new Course();
        course.setName(name);
        course.setInstructorId(Integer.parseInt(instructorId));

        // Add the course to the database
        courseDAO.addCourse(course);
    }

    public static void addStudent(String studentID , String courseiD) {
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        // Create the enrollment object
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(Integer.parseInt(studentID));
        enrollment.setCourseId(Integer.parseInt(courseiD));

        // add the enrollment to the database
        enrollmentDAO.addEnrollment(enrollment);
    }
}
