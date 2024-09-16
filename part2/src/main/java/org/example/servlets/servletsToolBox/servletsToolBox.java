package org.example.servlets.ServletsToolBox;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ServletsToolBox {
//    public static boolean login(String username, String hashedPassword, Statement stmt) throws SQLException {
//        String query = "SELECT * FROM User WHERE username = '" + username + "' AND password = '" + hashedPassword + "'";
//        ResultSet resultSet = stmt.executeQuery(query);
//
//        if (resultSet.next()) {
//            // User exists
//            return true;
//        } else {
//            // No user was found
//            return false;
//        }
//    }
//
//    public static String getUserRole(String username, String hashedPassword, Statement stmt) throws SQLException {
//        // Step 1: Write SQL query with WHERE clause to filter by username and password
//        String query = "SELECT Role.name AS role_name FROM User " +
//                "JOIN Role ON User.role_id = Role.id " +
//                "WHERE User.username = '" + username + "' AND User.password = '" + hashedPassword + "'";
//
//        // Step 2: Execute the query and get the result set
//        ResultSet resultSet = stmt.executeQuery(query);
//
//        // Step 3: Check if a row exists and retrieve the role name
//        if (resultSet.next()) {
//            return resultSet.getString("role_name");  // Return the role name
//        } else {
//            return null;  // User not found, return null
//        }
//    }
//
//    public static String hashPassword(String password) throws NoSuchAlgorithmException {
//        // Step 1: Get an instance of the SHA-256 MessageDigest
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//
//        // Step 2: Hash the password
//        byte[] hashBytes = md.digest(password.getBytes());
//
//        // Step 3: Convert the byte array into a hexadecimal string
//        StringBuilder hexString = new StringBuilder();
//        for (byte b : hashBytes) {
//            String hex = Integer.toHexString(0xff & b);
//            if (hex.length() == 1) hexString.append('0');
//            hexString.append(hex);
//        }
//
//        return hexString.toString();  // Return the hashed password in hexadecimal form
//    }

//    public static void createUser(String username, String role) throws NoSuchAlgorithmException{
//        // Assuming roleName is provided as "Admin"
//        RoleDAO roleDAO = new RoleDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");
//        Role wantedRole = null;
//        List<Role> roles = roleDAO.getAllRoles();
//        for (Role r : roles) {
//            if (r.getName().equalsIgnoreCase(role)) {
//                wantedRole = r;
//                break;
//            }
//        }
//
//        if (wantedRole != null) {
//            UserDAO userDAO = new UserDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");
//
//            // Create the User object
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(generatePassword());
//            user.setRoleId(wantedRole.getId()); // Set the Role ID
//
//            // Add the user to the database
//            userDAO.addUser(user);
//        } else {
//            System.out.println("Failed to create user because the role was not found.");
//        }
//    }
//
//    public static String generatePassword() throws NoSuchAlgorithmException {
//        String password = "12345";
//        return hashPassword(password);
//    }
//
//    public static void createCourse(String name, String instructorId) {
//        // create an instance
//        CourseDAO courseDAO = new CourseDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");
//
//        // Create the Course object
//        Course course = new Course();
//        course.setName(name);
//        course.setInstructorId(Integer.parseInt(instructorId));
//
//        // Add the course to the database
//        courseDAO.addCourse(course);
//    }
//
//    public static void addStudent(String studentID , String courseiD) {
//        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");
//
//        // Create the enrollment object
//        Enrollment enrollment = new Enrollment();
//        enrollment.setStudentId(Integer.parseInt(studentID));
//        enrollment.setCourseId(Integer.parseInt(courseiD));
//
//        // add the enrollment to the database
//        enrollmentDAO.addEnrollment(enrollment);
//    }
}
