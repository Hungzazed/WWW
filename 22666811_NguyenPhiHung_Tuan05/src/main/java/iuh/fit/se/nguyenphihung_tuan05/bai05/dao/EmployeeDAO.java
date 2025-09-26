package iuh.fit.se.nguyenphihung_tuan05.bai05.dao;

import iuh.fit.se.nguyenphihung_tuan05.bai05.model.Department;
import iuh.fit.se.nguyenphihung_tuan05.bai05.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);
    void update(Employee employee);
    void delete(int id);
    Employee findById(int id);
    List<Employee> findAll();
    List<Employee> findByDepartment(int departmentId);
}
