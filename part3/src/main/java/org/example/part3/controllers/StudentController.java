package org.example.part3.controllers;

import org.example.models.User;
import org.example.part3.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/api/student")
@SessionAttributes("username")  // This indicates that "username" is stored in session
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ModelAttribute("username")  // This allows us to bind the username to the session
    public String getUsername(HttpSession session) {
        return (String) session.getAttribute("username");
    }

    @GetMapping("/enrollments")
    public ResponseEntity<Object> getStudentEnrollments(@ModelAttribute("username") String username) {
        if (username == null) {
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
        }

        User currentUser = studentService.getCurrentUser(username);
        if (currentUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        int currentUserId = currentUser.getId();
        List<Map<String, Object>> enrollmentDetails = studentService.getEnrollmentDetails(currentUserId);
        Map<String, Double> gradeStats = studentService.getGradeStatistics(currentUserId);

        Map<String, Object> response = Map.of(
                "enrollmentDetails", enrollmentDetails,
                "gradeStats", gradeStats
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
