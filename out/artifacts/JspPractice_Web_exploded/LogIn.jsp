<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="16kb" autoFlush="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - CMS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="css/LogIn.css" rel="stylesheet">

</head>
<body>
<div class="login-container">
    <h2>Login to CMS</h2>
    <form method="POST" action="LogInServlet">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="roleDropdown">Role</label>
            <select class="form-select" id="roleDropdown" name="role" onchange="showSelectedRole()" required>
                <option value="" disabled selected>Select Role</option>
                <option value="employee">Admin</option>
                <option value="admin">Employee</option>
            </select>
            <label id="selectedRole" style="color: #0d6efd;"></label>
            <input type="hidden" name="selectedRole" id="hiddenRole">
        </div>
        <button type="submit" class="btn btn-primary btn-login">Login</button>
        <button type="button" class="btn btn-secondary btn-signup" onclick="window.location.href='SignUp.jsp'">Sign Up</button>
    </form>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error-message text-danger"><%= request.getAttribute("error") %></p>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="js/LogIn.js"></script>
</body>
</html>