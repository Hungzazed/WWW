<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Employee Information</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%" class="mb-3">

  <form action="${pageContext.request.contextPath}/employees" method="post">
    <input type="hidden" name="id" value="${employee.id}"/>

    <div class="mb-3">
      <label class="form-label">Name:</label>
      <input type="text" name="name" value="${employee.name}" class="form-control"/>
    </div>

    <div class="mb-3">
      <label class="form-label">Salary:</label>
      <input type="text" name="salary" value="${employee.salary}" class="form-control"/>
    </div>

    <div class="mb-3">
      <label class="form-label">Department:</label>
      <select name="departmentId" class="form-select">
        <c:forEach var="dep" items="${departments}">
          <option value="${dep.id}" <c:if test="${employee.department == dep.id}">selected</c:if>>
              ${dep.name}
          </option>
        </c:forEach>
      </select>
    </div>

    <button type="submit" class="btn btn-primary">Save</button>
  </form>
</div>
</body>
</html>
