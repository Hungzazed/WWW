<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration Result</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #eef2f7;
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 1200px;
            margin: auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 40px;
        }

        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #dddddd;
            text-align: left;
        }

        th {
            background-color: #f7f9fb;
            color: #333;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        ul {
            margin: 0;
            padding-left: 18px;
        }

        .no-data {
            text-align: center;
            color: #888;
            font-style: italic;
            padding: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Student Registration Details</h2>

    <c:choose>
        <c:when test="${not empty students}">
            <table>
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Pin Code</th>
                    <th>State</th>
                    <th>Country</th>
                    <th>Hobbies</th>
                    <th>Class X Board</th>
                    <th>Class X %</th>
                    <th>Class X Year</th>
                    <th>Class XII Board</th>
                    <th>Class XII %</th>
                    <th>Class XII Year</th>
                    <th>Course</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.dob}</td>
                        <td>${student.email}</td>
                        <td>${student.mobile}</td>
                        <td>${student.gender}</td>
                        <td>${student.address}</td>
                        <td>${student.city}</td>
                        <td>${student.pinCode}</td>
                        <td>${student.state}</td>
                        <td>${student.country}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty student.hobbies}">
                                    <ul>
                                        <c:forEach var="hobby" items="${student.hobbies}">
                                            <li>${hobby}</li>
                                        </c:forEach>
                                    </ul>
                                </c:when>
                                <c:otherwise>No hobbies selected</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${student.boardX}</td>
                        <td>${student.percentageX}</td>
                        <td>${student.yearX}</td>
                        <td>${student.boardXII}</td>
                        <td>${student.percentageXII}</td>
                        <td>${student.yearXII}</td>
                        <td>${student.course}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="no-data">No students registered yet.</div>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
