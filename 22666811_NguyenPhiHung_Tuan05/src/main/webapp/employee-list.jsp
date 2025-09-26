<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <%-- hoặc dùng uri="http://java.sun.com/jsp/jstl/core" nếu JSTL cũ --%>
<html>
<head>
    <title>Employees</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%">
    <h2 class="mt-3">Employees List</h2>
    <a class="btn btn-success mb-3" href="${pageContext.request.contextPath}/employees?action=new">Add Employee</a>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name Employee</th>
            <th>Salary</th>
            <th>Dept</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.salary}</td>
                <td>${emp.department}</td>
                <td>
                    <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/employees?action=edit&id=${emp.id}">Edit</a>
                    <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/employees?action=delete&id=${emp.id}"
                       onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/departments">Department</a>
</div>
</body>
</html>
