<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sessions List</title>
    <link rel="stylesheet" href="../css/show.css">
    <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>
<div class="glass-container">
    <div class="content">
        <h1 class="page-title">Sessions List</h1>
        <p class="page-description">Below is the list of all scheduled sessions:</p>
        <div id="session-list" class="exam-list">
            <!-- Sessions will be dynamically loaded here -->
        </div>
    </div>
</div>
<script src="../js/show-sessions.js"></script>
</body>
</html>
