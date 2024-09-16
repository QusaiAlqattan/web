<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 0;
            padding: 20px;
            height: 100vh; /* Full height for the page */
            overflow-y: auto; /* Scroll if content overflows */
        }

        h2 {
            text-align: center;
            margin-bottom: 30px; /* Space below the title */
            font-size: 2.5em;
            text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);
        }

        .form-container {
            background: rgba(0, 0, 0, 0.7);
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            width: 400px; /* Increased width for better layout */
            margin-bottom: 30px; /* Space between forms */
            text-align: left;
        }

        h3 {
            margin-bottom: 15px; /* Space below each section header */
            font-size: 1.8em;
        }

        label {
            display: block;
            margin-bottom: 8px; /* Space below labels */
            font-size: 1.1em;
        }

        input[type="text"], input[type="number"], select {
            width: 100%;
            padding: 10px; /* Padding for input fields */
            margin-bottom: 20px; /* Space below input fields */
            border: none;
            border-radius: 4px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            font-size: 1em; /* Font size for inputs */
        }

        button {
            background-color: #ff4081;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 12px;
            cursor: pointer;
            font-size: 1.1em;
            width: 100%; /* Full width button */
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #f50057;
        }
    </style>
</head>
<body>
<h2>Admin Dashboard</h2>

<div class="form-container">
    <h3>Create User</h3>
    <form action="admin" method="post">
        <input type="hidden" name="action" value="createUser">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="Admin">Admin</option>
            <option value="Teacher">Teacher</option>
            <option value="Student">Student</option>
        </select>
        <button type="submit">Create User</button>
    </form>
</div>

<div class="form-container">
    <h3>Create Course</h3>
    <form action="admin" method="post">
        <input type="hidden" name="action" value="createCourse">
        <label for="courseName">Course Name:</label>
        <input type="text" id="courseName" name="courseName" required>
        <label for="instructorID">Instructor ID:</label>
        <input type="number" id="instructorID" name="instructorID" required>
        <button type="submit">Create Course</button>
    </form>
</div>

<div class="form-container">
    <h3>Enroll Student in Course</h3>
    <form action="admin" method="post">
        <input type="hidden" name="action" value="enrollStudent">
        <label for="studentID">Student ID:</label>
        <input type="number" id="studentID" name="studentID" required>
        <label for="courseID">Course ID:</label>
        <input type="number" id="courseID" name="courseID" required>
        <button type="submit">Enroll Student</button>
    </form>
</div>
</body>
</html>
