//package org.example;
//
//import org.example.service.StudentServiceImplement;
//
//import java.net.ServerSocket;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.List;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//public class Main {
//    // MySQL connection details
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb"; // Replace with your DB URL
//    private static final String DB_USER = "root"; // Replace with your DB username
//    private static final String DB_PASSWORD = "12345"; // Replace with your DB password
//
//    static Connection  connection;
//
//    public static void main(String[] args) {
//
//        Connection dbConnection = null;
//        ServerSocket serverSocket = null;
//
//        try {
//            // 1. Load the JDBC Driver (Optional for modern JDBC)
//
//
//            // 2. Establish MySQL database connection
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            System.out.println("Successfully connected to th-e MySQL database!");
//
//
//            //new AdminServiceImplement(connection).enrollStudentInTheCourse("sarah","java");
//
//            //new InstructorServiceImplement(connection).enterStudentGrades("java","sarah",90);
//
//            //new AdminServiceImplement(connection).enrollStudentInTheCourse("sarah","python");
//            //new AdminServiceImplement(connection).createUser("razan","123456","student");
//
//            //new InstructorServiceImplement(connection).showInstructorCourses("asmaaa");
//            //List<String> x =new InstructorServiceImplement(connection).showInstructorCourses("asma");
//            List<String> x = new StudentServiceImplement(connection).showGrades("sarah");
//            //String x =new InstructorServiceImplement(connection).showStudentsInTheCourse("java","asma");
//
//            String xx =new StudentServiceImplement(connection).showGPA("sarah");
//
//            System.out.println(xx);
//
//            //String x =new StudentServiceImplement(connection).showGrades("sarah");
//            //List<String> x =new StudentServiceImplement(connection).viewAvailableCourses("razan");
//
//            System.out.println(x.toString());
//
//        } catch (Exception e) {
//            System.err.println("IO error: " + e.getMessage());
//            e.printStackTrace(); // Log full stack trace for debugging
//
//        }
//    }
//}
//
//
//
//
///*
//    @Override
//    public String createCourse(String courseName, String instructorName) {
//
//        Course course = new Course(courseName, instructorName);  // Create a new Course object
//        String instructorId = "-1";  // Default value for instructorId if not found
//        boolean addedCourse = false;
//
//        // SQL to fetch the instructorId by name
//        String findInstructorId = "SELECT userId FROM Users WHERE username = ? AND userType = 'Instructor'";
//
//        try (PreparedStatement statement = connection.prepareStatement(findInstructorId)) {
//            statement.setString(1, instructorName);  // Set the instructor name in the query
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    instructorId = resultSet.getString("userId");  // Get the userId of the instructor
//                } else {
//                    return "The instructor '" + instructorName + "' is not valid or does not exist.";
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException("An error occurred while searching for the instructor.", e);
//        }
//
//        // SQL to insert a new course
//        String sqlInsertCourse = "INSERT INTO Courses (courseName, instructorId) VALUES (?, ?)";
//
//        try (PreparedStatement statement = connection.prepareStatement(sqlInsertCourse)) {
//            statement.setString(1, course.getCourseName());  // Set the course name
//            statement.setString(2, instructorId);  // Set the instructorId fetched from the first query
//
//            int rowsAffected = statement.executeUpdate();  // Execute the insert query
//
//            if (rowsAffected > 0) {
//                addedCourse = true;  // Course was successfully added
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException("An error occurred while creating the course. Please try again later.", e);
//        }
//
//        // Return appropriate messages based on success or failure
//        if (!addedCourse) {
//            return "An error occurred while creating the course. Please try again later.";
//        } else {
//            return "Course created successfully: " + course.toString();  // Return the course details
//        }
//    }
//
//**/