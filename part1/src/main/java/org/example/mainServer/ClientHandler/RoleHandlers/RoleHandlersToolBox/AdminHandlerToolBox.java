package org.example.mainServer.ClientHandler.RoleHandlers.RoleHandlersToolBox;

import org.example.dao.*;
import org.example.dao.impl.CourseDAOImpl;
import org.example.dao.impl.EnrollmentDAOImpl;
import org.example.dao.impl.RoleDAOImpl;
import org.example.dao.impl.UserDAOImpl;
import org.example.models.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static org.example.mainServer.ClientHandler.ClientHandlerToolBox.ClientHandlerToolBox.hashPassword;

public abstract class AdminHandlerToolBox {
    // ! ***************************************************************
    // !   main functions
    // ! ***************************************************************
    public static void manageUsers(BufferedReader in, BufferedWriter out) throws IOException, NoSuchAlgorithmException {
        // send options
        String Option = """
                Please choose one of the following options:
                3- Edit users
                2- Delete users
                1- Create users
                0- Exit
                ---------------------------------
                """;
        out.write(Option);
        out.flush();

        // read user's input
        String line = in.readLine();
        switch(line) {
            case "3":
                updateUser(in ,out);
                break;
            case "2":
                deleteUser(in ,out);
                break;
            case "1":
                createUser(in ,out);
                break;
            case "0":
                System.exit(0);
                break;
        }
    }

    public static void manageCourses(BufferedReader in, BufferedWriter out) throws IOException, NoSuchAlgorithmException{
        // send options
        String Option = """
                Please choose one of the following options:
                3- Add students
                2- Delete courses
                1- Create courses
                0- Exit
                ---------------------------------
                """;
        out.write(Option);
        out.flush();

        // read user's input
        String line = in.readLine();
        switch(line) {
            case "3":
                addStudent(in ,out);
                break;
            case "2":
                deleteCourse(in ,out);
                break;
            case "1":
                createCourse(in ,out);
                break;
            case "0":
                System.exit(0);
                break;
        }
    }
    // ! ***************************************************************


    // ! ***************************************************************
    // !   manage users functions
    // ! ***************************************************************
    private static void createUser(BufferedReader in, BufferedWriter out) throws IOException, NoSuchAlgorithmException{
        // get the username
        String outputString = """
                Please enter the name of the user:
                ---------------------------------
                """;
        out.write(outputString);
        out.flush();
        String username = in.readLine();

        // get the role
        outputString = """
                Please enter the role of the user:
                ---------------------------------.
                """;
        out.write(outputString);
        out.flush();
        String role = in.readLine();

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

    private static String generatePassword() throws NoSuchAlgorithmException {
        String password = "12345";
        return hashPassword(password);
    }

    private static void deleteUser(BufferedReader in, BufferedWriter out) throws IOException, NoSuchAlgorithmException {
        // get the username
        String outputString = """
                Please enter the id of the user:
                ---------------------------------.
                """;
        out.write(outputString);
        out.flush();
        String id = in.readLine();

        UserDAO userDAO = new UserDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        // Add the user to the database
        userDAO.deleteUser(Integer.parseInt(id));
    }

    private static void updateUser(BufferedReader in, BufferedWriter out) throws IOException, NoSuchAlgorithmException {
        // get the id
        String outputString = """
                Please enter the id of the user:
                ---------------------------------
                """;
        out.write(outputString);
        out.flush();
        String id = in.readLine();

        // get the new username
        outputString = """
                Please enter the new name of the user:
                ---------------------------------
                """;
        out.write(outputString);
        out.flush();
        String username = in.readLine();

        // get the new password
        outputString = """
                Please enter the new password of the user:
                ---------------------------------
                """;
        out.write(outputString);
        out.flush();
        String password = hashPassword(in.readLine());

        // get the new role
        outputString = """
                Please enter the new role of the user:
                ---------------------------------.
                """;
        out.write(outputString);
        out.flush();
        String role = in.readLine();

        // Initialize RoleDAO
        RoleDAO roleDAO = new RoleDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        // Get the role ID by role name
        int roleId = roleDAO.getRoleIdByName(role);

        if (roleId != -1) {
            // Role was found, use the role ID for user operations
            UserDAO userDAO = new UserDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

            // Create a User object with the retrieved role ID
            User user = new User();
            user.setId(Integer.parseInt(id));
            user.setUsername(username);
            user.setPassword(password);
            user.setRoleId(roleId);

            // Add the user to the database
            userDAO.updateUser(user);
        } else {
            System.out.println("Role not found!");
        }
    }
    // ! ***************************************************************


    // ! ***************************************************************
    // !   manage courses functions
    // ! ***************************************************************
    private static void createCourse(BufferedReader in, BufferedWriter out) throws IOException{
        // get the name
        String outputString = """
                Please enter the name of the course:
                ---------------------------------
                """;
        out.write(outputString);
        out.flush();
        String name = in.readLine();

        // get the instructor
        outputString = """
                Please enter the id of the instructor:
                ---------------------------------.
                """;
        out.write(outputString);
        out.flush();
        String instructorId = in.readLine();


        // create an instance
        CourseDAO courseDAO = new CourseDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        // Create the Course object
        Course course = new Course();
        course.setName(name);
        course.setInstructorId(Integer.parseInt(instructorId));

        // Add the course to the database
        courseDAO.addCourse(course);
        }

    private static void deleteCourse(BufferedReader in, BufferedWriter out) throws IOException{
        // delete course
        // get the id
        String outputString = """
                Please enter the id of the course:
                ---------------------------------.
                """;
        out.write(outputString);
        out.flush();
        String id = in.readLine();

        CourseDAO courseDAO = new CourseDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        // remove the course to the database
        courseDAO.deleteCourse(Integer.parseInt(id));
    }

    private static void addStudent(BufferedReader in, BufferedWriter out) throws IOException{
        // get the id of the student
        String outputString = """
                Please enter the id of the student:
                ---------------------------------
                """;
        out.write(outputString);
        out.flush();
        String studentid = in.readLine();

        // get the id of the course
        outputString = """
                Please enter the id of the course:
                ---------------------------------.
                """;
        out.write(outputString);
        out.flush();
        String courseid = in.readLine();

        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        // Create the enrollment object
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(Integer.parseInt(studentid));
        enrollment.setCourseId(Integer.parseInt(courseid));

        // add the enrollment to the database
        enrollmentDAO.addEnrollment(enrollment);
    }
    // ! ***************************************************************
}
