<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Analytics</title>
    <link rel="stylesheet" type="text/css" href="../css/select.css">
    <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>
<div class="glass-container">
    <div class="page-title">View Student Results</div>
    <form method="get" action="/WebApp-1.0-SNAPSHOT/get-statistics" class="form-container">
        <select name="student" class="dropdown" id="studentDropdown" required>
            <!-- Options will be dynamically populated -->
        </select>
        <button type="submit" class="submit-button">Show Analytics</button>
    </form>
</div>
<script src="../js/fetch-student.js" defer></script>
</body>
</html>
