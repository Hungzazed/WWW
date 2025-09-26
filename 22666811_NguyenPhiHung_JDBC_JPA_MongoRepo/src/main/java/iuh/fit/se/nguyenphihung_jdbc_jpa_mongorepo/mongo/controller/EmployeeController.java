package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.controller;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model.Employee;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.findEmployeeById(id);
    }

    // Tìm nhân viên theo tên
    @GetMapping("/search/name")
    public ResponseEntity<List<Employee>> searchEmployeesByName(@RequestParam String name) {
        List<Employee> employees = employeeService.findEmployeesByName(name);
        return ResponseEntity.ok(employees);
    }

    // Tìm danh sách nhân viên theo ID phòng ban
    @GetMapping("/search/department/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable String departmentId) {
        List<Employee> employees = employeeService.findEmployeesByDepartmentId(departmentId);
        return ResponseEntity.ok(employees);
    }

    // Tìm danh sách nhân viên theo tên phòng ban
    @GetMapping("/search/department")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentName(@RequestParam String departmentName) {
        List<Employee> employees = employeeService.findEmployeesByDepartmentName(departmentName);
        return ResponseEntity.ok(employees);
    }

    // Tìm nhân viên theo khoảng lương
    @GetMapping("/search/salary/range")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(
            @RequestParam double minSalary,
            @RequestParam double maxSalary) {
        List<Employee> employees = employeeService.findEmployeesBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }

    // Tìm nhân viên có lương lớn hơn hoặc bằng
    @GetMapping("/search/salary/min")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryMin(@RequestParam double salary) {
        List<Employee> employees = employeeService.findEmployeesBySalaryGreaterThanEqual(salary);
        return ResponseEntity.ok(employees);
    }

    // Tìm nhân viên có lương nhỏ hơn hoặc bằng
    @GetMapping("/search/salary/max")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryMax(@RequestParam double salary) {
        List<Employee> employees = employeeService.findEmployeesBySalaryLessThanEqual(salary);
        return ResponseEntity.ok(employees);
    }

    // Tạo nhân viên mới
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // Cập nhật nhân viên
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        employee.setId(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Xóa nhân viên
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
