<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Session</title>
    <link rel="stylesheet" type="text/css" href="../css/select.css">
    <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>
<div class="glass-container">
    <div class="page-title">Add Session</div>
    <form method="post" action="/WebApp-1.0-SNAPSHOT/add-session" class="form-container">
        <select name="student" class="dropdown" id="studentDropdown" required>
            <!-- Options will be dynamically populated -->
        </select>
        <select name="exam" class="dropdown" id="examDropdown" required>
            <!-- Options will be dynamically populated -->
        </select>
        <button type="submit" class="submit-button">Add Exam</button>
    </form>
</div>
<script src="../js/fetch-exam.js" defer></script>
<script src="../js/fetch-student.js" defer></script>
</body>
</html>
