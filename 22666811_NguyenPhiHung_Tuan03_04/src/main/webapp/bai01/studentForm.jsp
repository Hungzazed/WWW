<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px auto;
            max-width: 900px;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h2 {
            text-align: center;
        }
        form {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
        }
        input[type=text], input[type=email], input[type=tel], input[type=date], textarea, select {
            width: 100%;
            padding: 8px;
            margin: 6px 0 12px;
            box-sizing: border-box;
        }
        label {
            font-weight: bold;
        }
        .row {
            display: flex;
            justify-content: space-between;
        }
        .row > div {
            width: 48%;
        }
        .checkbox-group, .radio-group {
            margin-bottom: 15px;
        }
        table {
            width: 100%;
            margin-top: 15px;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 8px;
            text-align: center;
        }
        .buttons {
            text-align: center;
            margin-top: 20px;
        }
        .buttons input {
            padding: 10px 20px;
            margin: 0 10px;
        }
    </style>
</head>
<body>
<h2>Student Registration Form</h2>
<form action="<%=request.getContextPath()%>/student_servlet" method="post">
    <div class="row">
        <div>
            <label>First Name</label>
            <input type="text" name="firstName" required>
        </div>
        <div>
            <label>Last Name</label>
            <input type="text" name="lastName" required>
        </div>
    </div>

    <div class="row">
        <div>
            <label>Date of Birth</label>
            <input type="date" name="dob" required>
        </div>
    </div>

    <div class="row">
        <div>
            <label>Email</label>
            <input type="email" name="email" required>
        </div>
        <div>
            <label>Mobile</label>
            <input type="tel" name="mobile" required>
        </div>
    </div>

    <div class="radio-group">
        <label>Gender</label><br>
        <input type="radio" name="gender" value="Male"> Male
        <input type="radio" name="gender" value="Female"> Female
    </div>

    <label>Address</label>
    <textarea name="address" rows="3" required></textarea>

    <div class="row">
        <div>
            <label>City</label>
            <input type="text" name="city" required>
        </div>
        <div>
            <label>Pin Code</label>
            <input type="text" name="pincode" required>
        </div>
    </div>

    <div class="row">
        <div>
            <label>State</label>
            <input type="text" name="state" required>
        </div>
        <div>
            <label>Country</label>
            <input type="text" name="country" value="India" required>
        </div>
    </div>

    <div class="checkbox-group">
        <label>Hobbies</label><br>
        <input type="checkbox" name="hobbies" value="Drawing"> Drawing
        <input type="checkbox" name="hobbies" value="Singing"> Singing
        <input type="checkbox" name="hobbies" value="Dancing"> Dancing
        <input type="checkbox" name="hobbies" value="Sketching"> Sketching
        <input type="checkbox" name="hobbies" value="Others"> Others
    </div>

    <h3>Qualification</h3>
    <table>
        <tr>
            <th>Sl.No</th>
            <th>Examination</th>
            <th>Board</th>
            <th>Percentage</th>
            <th>Year of Passing</th>
        </tr>
        <tr>
            <td>1</td>
            <td>Class X</td>
            <td><input type="text" name="boardX"></td>
            <td><input type="text" name="percentageX"></td>
            <td><input type="text" name="yearX"></td>
        </tr>
        <tr>
            <td>2</td>
            <td>Class XII</td>
            <td><input type="text" name="boardXII"></td>
            <td><input type="text" name="percentageXII"></td>
            <td><input type="text" name="yearXII"></td>
        </tr>
    </table>

    <h3>Course applies for</h3>
    <input type="radio" name="course" value="BCA"> BCA
    <input type="radio" name="course" value="B.Com"> B.Com
    <input type="radio" name="course" value="B.Sc"> B.Sc
    <input type="radio" name="course" value="B.A"> B.A

    <div class="buttons">
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </div>
</form>
</body>
</html>
