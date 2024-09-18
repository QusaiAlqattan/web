package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.utils.DataBaseManager;
import org.example.dao.*;
import org.example.dao.impl.*;
import org.example.models.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private Statement stmt;
    private DataBaseManager databaseManager;

    @Override
    public void init() throws ServletException {
        try {
            databaseManager = new DataBaseManager();
            Connection connection = databaseManager.getConnection();
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize database connection.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
            return;
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
                response.sendRedirect("index.jsp");
                return;
            }

            int currentUserId = currentUser.getId();

            // Fetch enrollments for current user
            List<Enrollment> currentUserEnrollments = enrollmentDAO.getEnrollmentByStudentId(currentUserId);
            List<Map<String, Object>> enrollmentDetails = new ArrayList<>();

            for (Enrollment enrollment : currentUserEnrollments) {
                String courseName = courseDAO.getCourseNameById(enrollment.getCourseId());

                Map<String, Object> enrollmentMap = new HashMap<>();
                enrollmentMap.put("courseName", courseName);
                enrollmentMap.put("grade", enrollment.getGrade());

                enrollmentDetails.add(enrollmentMap);
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
            if (databaseManager != null) {
                databaseManager.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
