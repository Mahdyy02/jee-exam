<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Statistics Results</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/result.css">
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/custom_scrollbar.css">
  <script src="<%= request.getContextPath() %>/js/get-result.js" defer></script>
</head>
<body>
<div class="glass-container">
  <h1>Student Performance Statistics</h1>

  <table border="1">
    <thead>
    <tr>
      <th>Speciality</th>
      <th>Mean Performance</th>
    </tr>
    </thead>
    <tbody>
    <!-- Table rows will be populated by JavaScript -->
    </tbody>
  </table>
</div>

<div id="jsonData" data-json='<%= request.getAttribute("jsonData") %>'></div>

</body>
</html>
