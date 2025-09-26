package iuh.fit.se.nguyenphihung_tuan05.bai05.dao.Impl;

import iuh.fit.se.nguyenphihung_tuan05.bai05.dao.EmployeeDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai05.model.Department;
import iuh.fit.se.nguyenphihung_tuan05.bai05.model.Employee;
import iuh.fit.se.nguyenphihung_tuan05.bai05.utils.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final DBUtil dbUtil;

    public EmployeeDAOImpl(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    @Override
    public void create(Employee employee) {
        String sql = "INSERT INTO employees (name, salary, department_id) VALUES (?, ?, ?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setDouble(2, employee.getSalary());
            stmt.setInt(3, employee.getDepartment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating employee", e);
        }
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, salary = ?, department_id = ? WHERE id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setDouble(2, employee.getSalary());
            stmt.setInt(3, employee.getDepartment());
            stmt.setInt(4, employee.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating employee", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    @Override
    public Employee findById(int id) {
        String sql = "SELECT e.id, e.name, e.salary, e.department_id, d.name as dept_name " +
                    "FROM employees e LEFT JOIN departments d ON e.department_id = d.id " +
                    "WHERE e.id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setName(rs.getString("name"));
                    employee.setSalary(rs.getDouble("salary"));
                    employee.setDepartment(rs.getInt("department_id"));
                    return employee;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding employee by id", e);
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT e.id, e.name, e.salary, e.department_id, d.name as dept_name " +
                    "FROM employees e LEFT JOIN departments d ON e.department_id = d.id";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartment(rs.getInt("department_id"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all employees", e);
        }

        return employees;
    }

    @Override
    public List<Employee> findByDepartment(int departmentId) {
        String sql = "SELECT e.id, e.name, e.salary, e.department_id, d.name as dept_name " +
                    "FROM employees e LEFT JOIN departments d ON e.department_id = d.id " +
                    "WHERE e.department_id = ?";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, departmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setName(rs.getString("name"));
                    employee.setSalary(rs.getInt("salary"));
                    employee.setDepartment(rs.getInt("department_id"));

                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding employees by department", e);
        }

        return employees;
    }
}
