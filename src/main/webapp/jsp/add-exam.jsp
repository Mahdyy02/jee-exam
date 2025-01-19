<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Exam</title>
    <link rel="stylesheet" type="text/css" href="../css/select.css">
    <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>
<div class="glass-container">
    <div class="page-title">Add Exam</div>
    <form method="post" action="/WebApp-1.0-SNAPSHOT/add-exam" class="form-container">
        <input type="text" name="examTitle" placeholder="Exam Title" class="input-field" required />
        <select name="speciality" class="dropdown" id="specialityDropdown" required>
            <!-- Options will be dynamically populated -->
        </select>
        <button type="submit" class="submit-button">Add Exam</button>
    </form>
</div>
<script src="../js/fetch-specialities.js"></script>
</body>
</html>
