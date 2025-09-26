<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="iuh.fit.se.nguyenphihung_tuan03_04.bai02.model.Account" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Registration Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px auto;
            max-width: 1000px;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .container {
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
            padding: 12px;
            text-align: left;
        }
        td {
            padding: 10px;
            text-align: left;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #e6f3ff;
        }
        .success-message {
            background-color: #dff0d8;
            border: 1px solid #d6e9c6;
            color: #3c763d;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .no-accounts {
            text-align: center;
            color: #666;
            font-style: italic;
            padding: 20px;
        }
        .action-links {
            text-align: center;
            margin-top: 20px;
        }
        .action-links a {
            display: inline-block;
            margin: 0 10px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .action-links a:hover {
            background-color: #45a049;
        }
        .account-count {
            background-color: #e7f3ff;
            border-left: 4px solid #2196F3;
            padding: 10px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Account Registration Results</h2>
        
        <%
            @SuppressWarnings("unchecked")
            List<Account> accounts = (List<Account>) request.getAttribute("accounts");
            
            if (accounts != null && !accounts.isEmpty()) {
        %>
            <div class="success-message">
                Account registration successful! Here are all registered accounts:
            </div>
            
            <div class="account-count">
                <strong>Total Accounts: <%= accounts.size() %></strong>
            </div>
            
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Date of Birth</th>
                        <th>Password</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        for (Account account : accounts) {
                    %>
                        <tr>
                            <td><%= account.getId() %></td>
                            <td><%= account.getFirstName() %></td>
                            <td><%= account.getLastName() %></td>
                            <td><%= account.getFullName() %></td>
                            <td><%= account.getEmail() %></td>
                            <td>
                                <%= account.getDateOfBirth() != null ? 
                                    account.getDateOfBirth().format(formatter) : 
                                    "Not specified" %>
                            </td>
                            <td>******* (Hidden for security)</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <%
            } else {
        %>
            <div class="no-accounts">
                No accounts found. Please register a new account.
            </div>
        <% } %>

        <div class="action-links">
            <a href="bai02/accountForm.jsp">Add New Account</a>
            <a href="account_servlet?action=list">Refresh List</a>
        </div>
    </div>
</body>
</html>