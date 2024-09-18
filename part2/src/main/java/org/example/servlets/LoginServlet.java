package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.service.LoginHandler;
import org.example.utils.DataBaseManager;

import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    Statement stmt;

    @Override
    public void init() throws ServletException {
        try {
            DataBaseManager dbManager = new DataBaseManager();
            Connection connection = dbManager.getConnection();
            this.stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize database connection.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        try {
            String hashedPassword = LoginHandler.hashPassword(password);

            boolean isUser = LoginHandler.login(username, hashedPassword, stmt);

            if (isUser) {
                String role = LoginHandler.getUserRole(username, hashedPassword, stmt);

                String redirectUrl;
                switch (role) {
                    case "Admin":
                        redirectUrl = "/admin";
                        break;
                    case "Teacher":
                        redirectUrl = "/teacher";
                        break;
                    case "Student":
                        redirectUrl = "/student";
                        break;
                    default:
                        redirectUrl = "/index.jsp";
                }
                response.sendRedirect(request.getContextPath() + redirectUrl);
            } else {
                request.setAttribute("errorMessage", "Invalid Username or Password, Try Again.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Database error occurred.", e);
        }
    }
}
