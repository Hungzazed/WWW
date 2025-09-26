package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.service;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.dao.DepartmentRepository;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.dao.EmployeeRepository;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model.Department;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    // Tìm nhân viên theo mã (ID)
    public Optional<Employee> findEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    // Tìm nhân viên theo tên
    public List<Employee> findEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Tìm danh sách nhân viên theo phòng ban (theo ID phòng ban)
    public List<Employee> findEmployeesByDepartmentId(String departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    // Tìm danh sách nhân viên theo tên phòng ban
    public List<Employee> findEmployeesByDepartmentName(String departmentName) {
        Department department = departmentRepository.findByNameIgnoreCase(departmentName);
        return employeeRepository.findByDepartment(department);
    }

    // Tìm nhân viên theo mức lương (khoảng lương)
    public List<Employee> findEmployeesBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    // Tìm nhân viên có lương lớn hơn hoặc bằng
    public List<Employee> findEmployeesBySalaryGreaterThanEqual(double salary) {
        return employeeRepository.findBySalaryGreaterThanEqual(salary);
    }

    // Tìm nhân viên có lương nhỏ hơn hoặc bằng
    public List<Employee> findEmployeesBySalaryLessThanEqual(double salary) {
        return employeeRepository.findBySalaryLessThanEqual(salary);
    }

    // Lưu nhân viên mới
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Cập nhật nhân viên
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Xóa nhân viên
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }
}
