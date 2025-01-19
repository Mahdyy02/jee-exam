<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Speciality Input</title>
    <link rel="stylesheet" type="text/css" href="../css/add.css">
    <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>

<div class="glass-container">
    <h1 class="page-title">Add Speciality</h1>
    <form action="/WebApp-1.0-SNAPSHOT/add-speciality" method="POST">
        <div class="form-container">
            <input type="text" name="specialtyName" class="input-field" placeholder="Enter Speciality Name" required>
            <button type="submit" class="submit-button">Add Speciality</button>
        </div>
    </form>
</div>

</body>
</html>
