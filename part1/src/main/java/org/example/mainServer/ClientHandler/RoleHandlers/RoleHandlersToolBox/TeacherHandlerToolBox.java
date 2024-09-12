package org.example.mainServer.ClientHandler.RoleHandlers.RoleHandlersToolBox;

import org.example.dao.EnrollmentDAO;
import org.example.dao.UserDAO;
import org.example.dao.impl.EnrollmentDAOImpl;
import org.example.dao.impl.UserDAOImpl;
import org.example.models.Enrollment;
import org.example.models.User;

import java.io.*;

public abstract class TeacherHandlerToolBox {
    public static void editGrades(BufferedReader in, BufferedWriter out) throws IOException {
        // send options
        String Option = """
                Please enter the student id
                ---------------------------------
                """;
        out.write(Option);
        out.flush();
        String studentId = in.readLine();

        Option = """
                Please enter the course id
                ---------------------------------
                """;
        out.write(Option);
        out.flush();
        String courseId = in.readLine();

        Option = """
                Please enter the the new grade
                ---------------------------------.
                """;
        out.write(Option);
        out.flush();
        String grade = in.readLine();

        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");

        Enrollment enrollment = new Enrollment();
        enrollment.setGrade(Integer.parseInt(grade));
        enrollment.setStudentId(Integer.parseInt(studentId));
        enrollment.setCourseId(Integer.parseInt(courseId));

        // Add the user to the database
        enrollmentDAO.updateEnrollment(enrollment);
    }
}
