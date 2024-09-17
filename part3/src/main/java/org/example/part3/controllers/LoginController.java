package org.example.part3.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.part3.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        // Create a ModelAndView object to return the view name
        return new ModelAndView("login");  // This should correspond to login.html
    }

    @PostMapping("/login")
    public String  login(@RequestParam String username,
                                        @RequestParam String password,
                                        HttpSession session) {
        try {
            boolean isUser = loginService.authenticateUser(username, password);

            if (isUser) {
                session.setAttribute("username", username);
                String userType = loginService.getUserRole(username, password);

                String redirectUrl;
                switch (userType) {
                    case "Admin":
                        return "admin";
                    case "Teacher":
                        return "teacher";
                    case "Student":
                        return "student";
                    default:
                        return "login";
                }
//                return new ResponseEntity<>(redirectUrl, HttpStatus.OK);
            } else {
//                return new ResponseEntity<>("Invalid Username or Password, Try Again.", HttpStatus.UNAUTHORIZED);
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace(); // For debugging purposes
            return "login";
        }
    }
}
