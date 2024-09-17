package org.example.part3.controllers;

import org.example.part3.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // Endpoint to edit grades of a student for a specific course
    @PostMapping("/edit-grade")
    public ResponseEntity<String> editGrade(
            @RequestParam String studentId,
            @RequestParam String courseId,
            @RequestParam String grade) {
        try {
            teacherService.editGrades(studentId, courseId, grade);
            return new ResponseEntity<>("Grade updated successfully.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update grade: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid input: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
