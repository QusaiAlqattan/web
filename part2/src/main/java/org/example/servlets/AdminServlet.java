package org.example.servlets;

import org.example.utils.DataBaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.service.AdminHandler.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    Statement stmt;
    DataBaseManager databaseManager;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            databaseManager = new DataBaseManager();
            Connection connection = databaseManager.getConnection();
            this.stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        switch (action) {
            case "createUser":
                String username = request.getParameter("username");
                String role = request.getParameter("role");

                try {
                    createUser(username, role);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "createCourse":
                String courseName = request.getParameter("courseName");
                String instructorID = request.getParameter("instructorID");
                createCourse(courseName, instructorID);
                break;

            case "enrollStudent":
                String studentID = request.getParameter("studentID");
                String courseID = request.getParameter("courseID");
                addStudent(studentID, courseID);
        }
        try {
            databaseManager.closeConnection();
            response.sendRedirect(request.getContextPath() + "/actionDone.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
