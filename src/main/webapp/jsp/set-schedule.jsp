<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set Exam Schedule</title>
    <link rel="stylesheet" href="../css/add.css">
</head>
<body>

<div class="glass-container">
    <h1 class="page-title">Set Exam Schedule</h1>

    <form action="/WebApp-1.0-SNAPSHOT/set-schedule" method="POST" class="form-container">
        <div>
            <h2 class="input-description">Number of Classrooms</h2>
            <input type="number" name="classroomsNumber" id="classroomsNumber" class="input-field" required>
        </div>

        <div>
            <h2 class="input-description">Begin Date</h2>
            <input type="date" name="beginDate" id="beginDate" class="input-field" required>
        </div>

        <div>
            <h2 class="input-description">End Date</h2>
            <input type="date" name="endDate" id="endDate" class="input-field" required>
        </div>

        <div>
            <button type="submit" class="submit-button">Set Schedule</button>
        </div>
    </form>
</div>

</body>
</html>
