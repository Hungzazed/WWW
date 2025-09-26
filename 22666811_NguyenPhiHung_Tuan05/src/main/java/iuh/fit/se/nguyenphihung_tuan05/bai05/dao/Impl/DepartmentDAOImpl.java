package iuh.fit.se.nguyenphihung_tuan05.bai05.dao.Impl;

import iuh.fit.se.nguyenphihung_tuan05.bai05.dao.DepartmentDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai05.model.Department;
import iuh.fit.se.nguyenphihung_tuan05.bai05.utils.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    private final DBUtil dbUtil;

    public DepartmentDAOImpl(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    @Override
    public void create(Department department) {
        String sql = "INSERT INTO departments (name) VALUES (?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, department.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating department", e);
        }
    }

    @Override
    public void update(Department department) {
        String sql = "UPDATE departments SET name = ? WHERE id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, department.getName());
            stmt.setInt(2, department.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating department", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM departments WHERE id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting department", e);
        }
    }

    @Override
    public Department findById(int id) {
        String sql = "SELECT id, name FROM departments WHERE id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Department department = new Department();
                    department.setId(rs.getInt("id"));
                    department.setName(rs.getString("name"));
                    return department;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding department by id", e);
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        String sql = "SELECT id, name FROM departments";
        List<Department> departments = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all departments", e);
        }

        return departments;
    }
}
