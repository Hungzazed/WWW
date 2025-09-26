package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.service;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.dao.DepartmentRepository;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.dao.EmployeeRepository;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model.Department;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model.Employee;
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
    public Optional<Employee> findEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
    public List<Employee> findEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Employee> findEmployeesByDepartmentId(int departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
    public List<Employee> findEmployeesByDepartmentName(String departmentName) {
        Department department = departmentRepository.findByNameIgnoreCase(departmentName);
        return employeeRepository.findByDepartment(department);
    }
    public List<Employee> findEmployeesBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }
    public List<Employee> findEmployeesBySalaryGreaterThanEqual(double salary) {
        return employeeRepository.findBySalaryGreaterThanEqual(salary);
    }
    public List<Employee> findEmployeesBySalaryLessThanEqual(double salary) {
        return employeeRepository.findBySalaryLessThanEqual(salary);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
