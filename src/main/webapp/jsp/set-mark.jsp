<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Set Mark</title>
  <link rel="stylesheet" href="../css/select.css">
  <link rel="stylesheet" type="text/css" href="../css/custom_scrollbar.css">
</head>
<body>
<div class="glass-container">
  <h1 class="page-title">Set Mark</h1>
  <div class="form-container">
    <form id="setMarkForm" action="/WebApp-1.0-SNAPSHOT/set-mark" method="POST">
      <select id="studentDropdown" class="dropdown" name="studentID" required>
        <option value="">Select Student</option>
      </select>
      <select id="examDropdown" class="dropdown" name="examID" required></select>
      <input type="number" class="input-field" name="mark" placeholder="Enter Mark" min="0" max="100" required>
      <button type="submit" class="submit-button">Submit</button>
    </form>
  </div>
</div>

<script src="../js/set-marks.js"></script>
</body>
</html>
