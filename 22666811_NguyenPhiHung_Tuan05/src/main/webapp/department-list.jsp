<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Departments List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%" class="mb-3">
  <h2>Departments List</h2>
  <a href="${pageContext.request.contextPath}/departments?action=new" class="btn btn-success mb-3">Add Department</a>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Department Name</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dep" items="${departments}">
      <tr>
        <td>${dep.id}</td>
        <td>${dep.name}</td>
        <td>
          <a href="${pageContext.request.contextPath}/departments?action=edit&id=${dep.id}" class="btn btn-warning btn-sm">Edit</a>
          <a href="${pageContext.request.contextPath}/departments?action=delete&id=${dep.id}" class="btn btn-danger btn-sm">Delete</a>
          <a href="${pageContext.request.contextPath}/employees?action=viewbyid&deptId=${dep.id}" class="btn btn-danger btn-sm">employees</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <a href="${pageContext.request.contextPath}/employees" class="btn btn-secondary">Back to Employees</a>
</div>
</body>
</html>
