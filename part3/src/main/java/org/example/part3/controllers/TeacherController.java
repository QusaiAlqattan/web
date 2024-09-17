package org.example.part3.controllers;

import org.example.part3.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // Endpoint to edit grades of a student for a specific course
    @PostMapping("/edit-grade")
    public ModelAndView editGrade(
            @RequestParam String studentId,
            @RequestParam String courseId,
            @RequestParam String grade) {
        ModelAndView modelAndView = new ModelAndView("actionDone"); // Name of the template to render
        try {
            teacherService.editGrades(studentId, courseId, grade);
            modelAndView.addObject("message", "Grade updated successfully.");
        } catch (IOException e) {
            modelAndView.addObject("message", "Failed to update grade: " + e.getMessage());
        } catch (NumberFormatException e) {
            modelAndView.addObject("message", "Invalid input: " + e.getMessage());
        }
        return modelAndView; // Return the view
    }
}

