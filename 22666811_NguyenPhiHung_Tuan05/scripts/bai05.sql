CREATE
    DATABASE IF NOT EXISTS hrdb;
USE
    hrdb;

-- Bảng Department
CREATE TABLE departments
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Bảng Employee
CREATE TABLE employees
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100)   NOT NULL,
    salary        DECIMAL(10, 2) NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE SET NULL
);

-- Dữ liệu Departments
INSERT INTO departments (name)
VALUES ('Human Resources'),
       ('Finance'),
       ('IT'),
       ('Marketing'),
       ('Sales');

-- Dữ liệu Employees
INSERT INTO employees (name, salary, department_id)
VALUES ('Nguyen Van A', 1200.50, 1),
       ('Tran Thi B', 1500.00, 2),
       ('Le Van C', 1800.75, 3),
       ('Pham Thi D', 2000.00, 3),
       ('Hoang Van E', 1700.25, 4),
       ('Do Thi F', 1600.00, 5);
