<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Courses and Grades</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
            text-align: center;
        }

        h2 {
            margin-bottom: 30px;
            font-size: 2.5em;
            text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin: 0 auto;
            background: rgba(0, 0, 0, 0.7);
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #ff4081;
            color: white;
            font-size: 1.2em;
        }

        tr:hover {
            background-color: rgba(255, 255, 255, 0.1);
        }

        p {
            font-size: 1.2em;
            margin: 20px 0;
        }

        .stats {
            margin-top: 30px;
            font-size: 1.3em;
            text-shadow: 1px 1px 5px rgba(0, 0, 0, 0.5);
        }
    </style>
</head>
<body>
<h2>Enrolled Courses and Grades</h2>

<!-- Check if enrollmentDetails is not empty -->
<c:if test="${not empty enrollmentDetails}">
    <table>
        <thead>
        <tr>
            <th>Course Name</th>
            <th>Grade</th>
        </tr>
        </thead>
        <tbody>
        <!-- Iterate over enrollmentDetails list -->
        <c:forEach var="enrollment" items="${enrollmentDetails}">
            <tr>
                <td>${enrollment.courseName}</td>
                <td>${enrollment.grade}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<!-- Message if no enrollments are found -->
<c:if test="${empty enrollmentDetails}">
    <p>No courses found for the student.</p>
</c:if>

<!-- Display grade statistics if available -->
<c:if test="${not empty gradeStats}">
    <div class="stats">
        <p><strong>Grade Statistics:</strong></p>
        <p>Max Grade: ${gradeStats.maxGrade}</p>
        <p>Min Grade: ${gradeStats.minGrade}</p>
        <p>Average Grade: ${gradeStats.avgGrade}</p>
    </div>
</c:if>
</body>
</html>
