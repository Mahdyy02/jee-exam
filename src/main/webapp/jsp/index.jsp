<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/custom_scrollbar.css">
</head>

<body>
<div class="glass-container">
    <h2>Login</h2>
    <form action="/WebApp-1.0-SNAPSHOT/login" method="POST">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Login</button>
    </form>

    <!-- Display error message if login fails -->
    <c:if test="${not empty errorMessage}">
        <p style="color: #000000;">${errorMessage}</p>
    </c:if>

</div>
</body>
</html>
