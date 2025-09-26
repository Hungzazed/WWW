<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px auto;
            max-width: 600px;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .form-container {
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type=text], input[type=email], input[type=password], input[type=date] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input[type=text]:focus, input[type=email]:focus, input[type=password]:focus, input[type=date]:focus {
            border-color: #4CAF50;
            outline: none;
        }
        .row {
            display: flex;
            gap: 15px;
        }
        .row > div {
            flex: 1;
        }
        .submit-btn {
            background-color: #4CAF50;
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            margin-top: 10px;
        }
        .submit-btn:hover {
            background-color: #45a049;
        }
        .reset-btn {
            background-color: #f44336;
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            margin-top: 10px;
        }
        .reset-btn:hover {
            background-color: #da190b;
        }
        .button-group {
            display: flex;
            gap: 10px;
        }
        .button-group > div {
            flex: 1;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Account Registration Form</h2>
        <form action="<%=request.getContextPath()%>/account_servlet" method="post">
            <div class="row">
                <div>
                    <div class="form-group">
                        <label for="firstName">First Name:</label>
                        <input type="text" id="firstName" name="firstName" required>
                    </div>
                </div>
                <div>
                    <div class="form-group">
                        <label for="lastName">Last Name:</label>
                        <input type="text" id="lastName" name="lastName" required>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="dateOfBirth">Date of Birth:</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" required>
            </div>

            <div class="button-group">
                <div>
                    <input type="submit" value="Register Account" class="submit-btn">
                </div>
                <div>
                    <input type="reset" value="Reset Form" class="reset-btn">
                </div>
            </div>
        </form>

        <div style="margin-top: 20px; text-align: center;">
            <a href="account_servlet?action=list" style="color: #4CAF50; text-decoration: none;">View All Accounts</a>
        </div>
    </div>
</body>
</html>