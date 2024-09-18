package org.example.part3.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.part3.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public String  login(@RequestParam String username,
                                        @RequestParam String password,
                                        HttpSession session) {
        try {
            boolean isUser = loginService.authenticateUser(username, password);

            if (isUser) {
                session.setAttribute("username", username);
                String role = loginService.getUserRole(username, password);

                String redirectUrl;
                switch (role) {
                    case "Admin":
                        return "admin";
                    case "Teacher":
                        return "teacher";
                    case "Student":
                        return "student";
                    default:
                        return "login";
                }
            } else {
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace(); // For debugging purposes
            return "login";
        }
    }
}
