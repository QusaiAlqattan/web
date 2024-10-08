package org.example.servlets;

import org.example.utils.DataBaseManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.service.TeacherHandler.editGrades;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

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
        request.getRequestDispatcher("/teacher.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentId = request.getParameter("studentID");
        String courseId = request.getParameter("courseID");
        String grade = request.getParameter("Grade");

        // Perform the action (e.g., edit the grade)
        editGrades(studentId, courseId, grade);

        try {
            databaseManager.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/actionDone.jsp");
    }
}
