package org.example.dao;

import org.example.models.Course;

import java.util.List;

public interface CourseDAO {
    void addCourse(Course course);                  // Add a new course
    Course getCourse(int id);                       // Retrieve a course by its ID
    List<Course> getAllCourses();                   // Retrieve all courses
    void updateCourse(Course course);               // Update an existing course
    void deleteCourse(int id);                      // Delete a course by its ID
    String getCourseNameById(int courseId);
}
