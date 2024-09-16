package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.DataBaseManager;
import org.example.dao.CourseDAO;
import org.example.dao.EnrollmentDAO;
import org.example.dao.UserDAO;
import org.example.dao.impl.CourseDAOImpl;
import org.example.dao.impl.EnrollmentDAOImpl;
import org.example.dao.impl.UserDAOImpl;
import org.example.models.Enrollment;
import org.example.models.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private Statement stmt;
    private DataBaseManager dbManager;

    @Override
    public void init() throws ServletException {
        try {
            dbManager = new DataBaseManager();
            Connection connection = dbManager.getConnection();
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize database connection.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;  // Stop further processing
        }

        String username = (String) session.getAttribute("username");

        try {
            // Initialize DAOs
            UserDAO userDAO = new UserDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            CourseDAO courseDAO = new CourseDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

            // Fetch current user
            User currentUser = userDAO.getUserByUsername(username);
            if (currentUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int currentUserId = currentUser.getId();

            // Fetch enrollments for current user
            List<Enrollment> currentUserEnrollments = enrollmentDAO.getEnrollmentByStudentId(currentUserId);
            List<Map<String, Object>> enrollmentDetails = new ArrayList<>();

            for (Enrollment enrollment : currentUserEnrollments) {
                String courseName = courseDAO.getCourseNameById(enrollment.getCourseId());

                Map<String, Object> enrollmentMap = new HashMap<>();
                enrollmentMap.put("courseName", courseName);  // Add the course name
                enrollmentMap.put("grade", enrollment.getGrade());  // Add the grade

                enrollmentDetails.add(enrollmentMap);  // Add the map to the list
            }

            Map<String, Double> gradeStats = enrollmentDAO.getGradeStatisticsByStudentId(currentUserId);

            // Pass the list to the JSP
            request.setAttribute("enrollmentDetails", enrollmentDetails);
            request.setAttribute("gradeStats", gradeStats);
            request.getRequestDispatcher("/student.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Error accessing the database.", e);
        }
    }

    @Override
    public void destroy() {
        try {
            if (dbManager != null) {
                dbManager.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
