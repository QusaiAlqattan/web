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
            height: 100vh; /* Full height for the page */
            margin: 0;
            padding: 20px;
            text-align: center; /* Center text for a clean look */
        }

        h2 {
            margin-bottom: 30px; /* Space below the title */
            font-size: 2.5em; /* Size of the heading */
            text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);
        }

        table {
            width: 80%; /* Responsive width for the table */
            border-collapse: collapse; /* Collapse borders for cleaner look */
            margin: 0 auto; /* Center table */
            background: rgba(0, 0, 0, 0.7); /* Background for table */
            border-radius: 10px; /* Rounded corners for table */
            overflow: hidden; /* Hide overflow for rounded corners */
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
        }

        th, td {
            padding: 12px; /* Padding for table cells */
            text-align: left; /* Align text to the left */
            border-bottom: 1px solid #ddd; /* Bottom border for rows */
        }

        th {
            background-color: #ff4081; /* Header background color */
            color: white; /* Header text color */
            font-size: 1.2em; /* Font size for header */
        }

        tr:hover {
            background-color: rgba(255, 255, 255, 0.1); /* Hover effect for rows */
        }

        p {
            font-size: 1.2em; /* Font size for the paragraph */
            margin: 20px 0; /* Space above and below the paragraph */
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
</body>
</html>
