<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List</title>
    <link rel="stylesheet" href="../css/show.css">
    <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>
<div class="glass-container">
    <div class="content">
        <h1 class="page-title">Student List</h1>
        <p class="page-description">Below is the list of all registered students:</p>
        <div id="student-list" class="exam-list">
            <!-- Students will be dynamically loaded here -->
        </div>
    </div>
</div>
<script src="../js/show-students.js"></script>
</body>
</html>
