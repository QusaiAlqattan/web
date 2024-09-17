package org.example.part3.controllers;

import org.example.models.Course;
import org.example.part3.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Create a new user
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(
            @RequestParam String username,
            @RequestParam String role) {
        try {
            adminService.createUser(username, role);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user: " + e.getMessage());
        }
    }

    // Create a new course
    @PostMapping("/createCourse")
    public ResponseEntity<String> createCourse(
            @RequestParam String name,
            @RequestParam String instructorId) {
        try {
            adminService.createCourse(name, instructorId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create course: " + e.getMessage());
        }
    }

    // Enroll a student in a course
    @PostMapping("/enrollStudent")
    public ResponseEntity<String> enrollStudent(
            @RequestParam String studentId,
            @RequestParam String courseId) {
        try {
            adminService.addStudent(studentId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student enrolled successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to enroll student: " + e.getMessage());
        }
    }
}
