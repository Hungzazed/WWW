<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Department Form</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%" class="mb-3">
  <h2>Department Information</h2>

  <form action="${pageContext.request.contextPath}/departments" method="post">
    <input type="hidden" name="id" value="${department.id}"/>

    <div class="mb-3">
      <label class="form-label">Department Name:</label>
      <input type="text" name="name" value="${department.name}" class="form-control"/>
    </div>

    <button type="submit" class="btn btn-primary">Save</button>
    <a href="${pageContext.request.contextPath}/departments" class="btn btn-secondary">Cancel</a>
  </form>
</div>
</body>
</html>
