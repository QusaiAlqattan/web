<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
            flex-direction: column; /* Align children in a column */
        }

        h2 {
            text-align: center;
            margin-bottom: 20px; /* Adjusted margin */
            font-size: 2.5em;
            text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);
        }

        .form-container {
            background: rgba(0, 0, 0, 0.7);
            border-radius: 10px; /* Slightly increased border-radius for smoother edges */
            padding: 30px; /* Increased padding for a more spacious feel */
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            width: 350px; /* Increased width for better layout */
            text-align: left; /* Align text to the left for consistency */
        }

        label {
            display: block;
            margin-bottom: 10px; /* Adjusted margin for label spacing */
            font-size: 1.2em;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px; /* Increased padding for input fields */
            margin-bottom: 20px; /* Maintained margin for consistency */
            border: none;
            border-radius: 4px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        input[type="submit"] {
            background-color: #ff4081;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 12px; /* Increased padding for the button */
            cursor: pointer;
            font-size: 1.2em;
            transition: background-color 0.3s ease;
            width: 100%; /* Ensured button spans the full width */
        }

        input[type="submit"]:hover {
            background-color: #f50057;
        }

        .error-message {
            color: #ffcc00; /* Bright yellow for visibility */
            text-align: center;
            margin-top: 10px;
            font-size: 1em;
        }
    </style>
</head>
<body>
<h2>Login</h2>
<div class="form-container">
    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <input type="submit" value="Login">
    </form>
    <c:if test="${not empty errorMessage}">
        <p class="error-message">${errorMessage}</p>
    </c:if>
</div>
</body>
</html>
