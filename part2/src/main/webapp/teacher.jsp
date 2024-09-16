<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Instructor Dashboard</title>
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

        h3 {
            margin-bottom: 30px; /* Space below the heading */
            font-size: 2.5em; /* Size of the heading */
            text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);
        }

        form {
            background: rgba(0, 0, 0, 0.7); /* Semi-transparent background for form */
            border-radius: 10px;
            padding: 30px; /* Padding for spaciousness */
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            width: 400px; /* Fixed width for the form */
            display: flex; /* Flexbox for form layout */
            flex-direction: column; /* Stack elements vertically */
            align-items: center; /* Center form elements */
        }

        label {
            align-self: flex-start; /* Align labels to the start */
            margin-bottom: 5px; /* Space below labels */
            font-size: 1.1em; /* Font size for labels */
        }

        input[type="number"] {
            width: 100%; /* Full width for input fields */
            padding: 10px; /* Padding for input fields */
            margin-bottom: 20px; /* Space below input fields */
            border: none; /* Remove default border */
            border-radius: 4px; /* Rounded corners for input fields */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            font-size: 1em; /* Font size for inputs */
        }

        button {
            background-color: #ff4081; /* Button color */
            color: white; /* Button text color */
            border: none; /* Remove border */
            border-radius: 4px; /* Rounded corners for button */
            padding: 12px; /* Padding for button */
            cursor: pointer; /* Pointer cursor on hover */
            font-size: 1.1em; /* Font size for button */
            transition: background-color 0.3s ease; /* Smooth transition for hover effect */
            width: 100%; /* Full width button */
        }

        button:hover {
            background-color: #f50057; /* Button hover color */
        }
    </style>
</head>
<body>
<h3>Edit Grades</h3>
<form action="teacher" method="post">
    <label for="studentID">Student ID:</label>
    <input type="number" id="studentID" name="studentID" required>

    <label for="courseID">Course ID:</label>
    <input type="number" id="courseID" name="courseID" required>

    <label for="Grade">Grade:</label>
    <input type="number" id="Grade" name="Grade" required>

    <button type="submit">Edit</button>
</form>
</body>
</html>
