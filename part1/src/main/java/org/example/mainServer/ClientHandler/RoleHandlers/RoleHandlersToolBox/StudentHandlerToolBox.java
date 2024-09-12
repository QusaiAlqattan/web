package org.example.mainServer.ClientHandler.RoleHandlers.RoleHandlersToolBox;

import org.example.dao.EnrollmentDAO;
import org.example.dao.impl.EnrollmentDAOImpl;
import org.example.models.Enrollment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public abstract class StudentHandlerToolBox {
    public static void showGrades(BufferedReader in, BufferedWriter out) throws IOException {
        String initialOption = """
                Please enter the student's id:
                ---------------------------------
                """;
        out.write(initialOption);
        out.flush();
        String studentId = in.readLine();

        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl("jdbc:mysql://localhost:3306/mydb", "root", "12345");
        List<Enrollment> studentEnrolls = enrollmentDAO.getEnrollmentByStudentId(Integer.parseInt(studentId));

        String output = "";
        for (Enrollment enrollment : studentEnrolls) {
            output = output + "Course ID: " + enrollment.getCourseId() + ", Grade: " + enrollment.getGrade() +"\n";
        }
        output += "---------------------------------.";
        out.write(output);
        out.flush();
    }
}
