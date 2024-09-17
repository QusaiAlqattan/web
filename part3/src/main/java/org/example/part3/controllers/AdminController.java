package org.example.part3.controllers;

import org.example.models.Course;
import org.example.part3.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Create a new user
    @PostMapping("/createUser")
    public ModelAndView createUser(
            @RequestParam String username,
            @RequestParam String role) {
        ModelAndView modelAndView = new ModelAndView("actionDone"); // Name of the template to render
        try {
            adminService.createUser(username, role);
            modelAndView.addObject("message", "User created successfully.");
        } catch (NoSuchAlgorithmException e) {
            modelAndView.addObject("message", "Error creating user: " + e.getMessage());
        } catch (Exception e) {
            modelAndView.addObject("message", "Failed to create user: " + e.getMessage());
        }
        return modelAndView; // Return the view
    }

    // Create a new course
    @PostMapping("/createCourse")
    public ModelAndView createCourse(
            @RequestParam String name,
            @RequestParam String instructorId) {
        ModelAndView modelAndView = new ModelAndView("actionDone");
        try {
            adminService.createCourse(name, instructorId);
            modelAndView.addObject("message", "Course created successfully.");
        } catch (Exception e) {
            modelAndView.addObject("message", "Failed to create course: " + e.getMessage());
        }
        return modelAndView;
    }

    // Enroll a student in a course
    @PostMapping("/enrollStudent")
    public ModelAndView enrollStudent(
            @RequestParam String studentId,
            @RequestParam String courseId) {
        ModelAndView modelAndView = new ModelAndView("actionDone");
        try {
            adminService.addStudent(studentId, courseId);
            modelAndView.addObject("message", "Student enrolled successfully.");
        } catch (Exception e) {
            modelAndView.addObject("message", "Failed to enroll student: " + e.getMessage());
        }
        return modelAndView;
    }
}
