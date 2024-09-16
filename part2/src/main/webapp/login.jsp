<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <link rel="stylesheet" href="styles.css"> <!-- Optional CSS for styling -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h1 {
            color: #333;
        }
        form {
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        .error-message {
            color: red;
        }
    </style>
</head>
<body>

<h1>Admin Login</h1>

<%
    // Display error message if it exists
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<p class="error-message"><%= errorMessage %></p>
<%
    }
%>

<form method="post" action="admin">
    <input type="hidden" name="action" value="login"> <!-- Hidden field for action -->
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <label for="role">Role:</label>
    <select id="role" name="role" required>
        <option value="admin">Admin</option>
        <option value="instructor">Instructor</option>
        <option value="student">Student</option>
        <!-- Add other roles as needed -->
    </select>

    <button type="submit">Login</button>
</form>

</body>
</html>
