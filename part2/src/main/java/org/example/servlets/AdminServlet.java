package org.example.servlets;

import org.example.DataBaseManager;
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

//    private AdminServiceImplement adminService;
    Statement stmt;
    DataBaseManager dbManager;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dbManager = new DataBaseManager();
            Connection connection = dbManager.getConnection();
            this.stmt = connection.createStatement();
//            adminService = new AdminServiceImplement(connection);
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize database connection.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        switch (action) {
            case "createUser":
                String username = request.getParameter("username");
                String role = request.getParameter("role");
//                String hashedPassword = "";
//                try{
//                    hashedPassword = hashPassword(request.getParameter("password"));
//                }catch (NoSuchAlgorithmException e){
//                    throw new ServletException("Failed to initialize database connection.", e);
//                }

                try {
                    createUser(username, role);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
//                message = adminService.createUser(username, password, userType);
                break;
            case "createCourse":
                String courseName = request.getParameter("courseName");
                String instructorID = request.getParameter("instructorID");
                createCourse(courseName, instructorID);
//                message = adminService.createCourse(courseName, instructorID);
                break;
            case "enrollStudent":
                String studentID = request.getParameter("studentID");
                String courseID = request.getParameter("courseID");
                addStudent(studentID, courseID);
        }
        try {
            dbManager.closeConnection();
            response.sendRedirect(request.getContextPath() + "/actionDone.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
//        if ("createUser".equals(action)) {
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//            String userType = request.getParameter("userType");
//            message = adminService.createUser(username, password, userType);
//
//        } else if ("createCourse".equals(action)) {
//            String courseName = request.getParameter("courseName");
//            String instructorUsername = request.getParameter("instructorUsername");
//            message = adminService.createCourse(courseName, instructorUsername);
//
//        }

//        request.setAttribute("message", message);
//        request.getRequestDispatcher("adminResult.jsp").forward(request, response)
    }
}
