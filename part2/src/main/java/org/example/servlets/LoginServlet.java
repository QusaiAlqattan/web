package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.DataBaseManager;
import org.example.mainServer.ClientHandler.RoleHandlers.LoginHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    Statement stmt;

    public LoginServlet() {
        super();
    }

//    private UserAuthenticationService userAuthenticationService;

    @Override
    public void init() throws ServletException {
        try {
            DataBaseManager dbManager = new DataBaseManager();
            Connection connection = dbManager.getConnection();
            this.stmt = connection.createStatement();
//            userAuthenticationService = new UserAuthenticationService(connection);
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize database connection.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        try {
            String hashedPassword = LoginHandler.hashPassword(password);

            boolean isUser = LoginHandler.login(username, hashedPassword, stmt);

            if (isUser) {
                String userType = LoginHandler.getUserRole(username, hashedPassword, stmt);

                String redirectUrl;
                switch (userType) {
                    case "Admin":
                        redirectUrl = "/admin.jsp";
                        response.sendRedirect(request.getContextPath() + redirectUrl);
                        break;
                    case "Teacher":
                        redirectUrl = "/teacher.jsp";
                        response.sendRedirect(request.getContextPath() + redirectUrl);
                        break;
                    case "Student":
                        redirectUrl = "/student";
                        response.sendRedirect(request.getContextPath() +redirectUrl);
                        break;
                    default:
                        redirectUrl = "/index.jsp";
                        response.sendRedirect(request.getContextPath() + redirectUrl);
                }

//                String redirectUrl;
//                if ("Student".equalsIgnoreCase(userType)) {
//                    redirectUrl = "/student.jsp";
//                } else if ("Admin".equalsIgnoreCase(userType)) {
//                    redirectUrl = "/admin.jsp";
//                } else if ("Instructor".equalsIgnoreCase(userType)) {
//                    redirectUrl = "/teacher.jsp";
//                } else {
//                    redirectUrl = "/index.jsp";
//                }
            } else {
                request.setAttribute("errorMessage", "Invalid Username or Password, Try Again.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Debugging line for catching SQL exception
            throw new ServletException("Database error occurred.", e);
        }
    }
}
