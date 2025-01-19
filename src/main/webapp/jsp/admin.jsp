<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" type="text/css" href="../css/admin.css">
    <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>
<div class="glass-container">
    <nav class="navbar">
        <ul>
            <li class="nav-item">
                <a href="">Add</a>
                <ul class="dropdown">
                    <li><a href="add-session.jsp">Add Session</a></li>
                    <li><a href="add-student.jsp">Add Student</a></li>
                    <li><a href="add-exam.jsp">Add Exam</a></li>
                    <li><a href="add-speciality.jsp">Add Speciality</a></li>
                    <li><a href="set-mark.jsp">Set Mark</a></li>
                    <li><a href="set-schedule.jsp">Set Schedule</a></li>
                </ul>
            </li>

            <li class="nav-item">
                <a href="">View</a>
                <ul class="dropdown">
                    <li><a href="view-sessions.jsp">View Sessions</a></li>
                    <li><a href="view-students.jsp">View Students</a></li>
                    <li><a href="view-exams.jsp">View Exams</a></li>
                    <li><a href="view-specialities.jsp">View Specialities</a></li>
                </ul>
            </li>

            <li class="nav-item"><a href="analytics.jsp">Analytics</a></li>
        </ul>
    </nav>
    <div class="content">
        <h2 class="page-title">Admin Dashboard</h2>
        <p class="page-description">Welcome to the admin page. Use the navigation bar to manage sessions, students, exams, and more.</p>
    </div>
</div>

<script src="../js/scripts.js"></script>

</body>
</html>
