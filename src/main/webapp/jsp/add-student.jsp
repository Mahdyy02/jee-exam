<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
    <link rel="stylesheet" type="text/css" href="../css/add.css">
    <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>

<div class="glass-container">
    <h1 class="page-title">Add Student</h1>
    <form action="/WebApp-1.0-SNAPSHOT/add-student" method="POST">
        <div class="form-container">
            <input type="text" name="studentName" class="input-field" placeholder="Enter Student Name" required>
            <input type="text" name="studentCIN" class="input-field" placeholder="Enter Student CIN" required>
            <button type="submit" class="submit-button">Add Student</button>
        </div>
    </form>
</div>

</body>
</html>
