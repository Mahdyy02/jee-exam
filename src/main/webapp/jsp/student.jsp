<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Student Page</title>
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/student.css">
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/custom_scrollbar.css">
</head>
<body>
<div class="glass-container">
  <nav class="navbar">
    <ul>
      <li class="nav-item">
        <a href="student-schedule.jsp">All Sessions</a>
      </li>
      <li class="nav-item">
        <a href="student-means.jsp">Specialty Average</a>
      </li>
      <li class="nav-item">
        <a href="student-exams.jsp">Future Exams</a>
      </li>
    </ul>
  </nav>
  <div class="content">
    <h2>Welcome to the Student Portal</h2>
    <p>Use the navigation bar to switch between pages.</p>
  </div>
</div>
</body>
</html>